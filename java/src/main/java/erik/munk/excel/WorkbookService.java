package erik.munk.excel;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import erik.munk.service.ParamService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class WorkbookService {

	private static final String REGEX_DATE_EU = "[0-9]{2}/[0-9]{2}/[0-9]{4}";

	@Autowired
	private ParamService paramService;

	private String siretCell;

	private Integer indexLineEndHeaders;

	private String startPeriodeCell;

	@PostConstruct
	public void init() {
		siretCell = paramService.getString(ParamKey.KEY_EDC_EXCEL_CELL_WITH_SIRET);
		indexLineEndHeaders = paramService.getInteger(ParamKey.KEY_EDC_EXCEL_INDEX_LINE_END_HEADERS);
		startPeriodeCell = paramService.getString(ParamKey.KEY_EDC_EXCEL_CELL_WITH_START_PERIODE);
	}

	/**
	 * Permet de récupérer facilement la feuille excel du template
	 * @return La feuille unique du fichier excel charger passé en argument
	 */
	public Sheet getWorkingSheet(final Workbook workbook) {
		if (workbook.getNumberOfSheets() == 1) {
			return workbook.getSheetAt(0);
		} else {
			throw new RuntimeException("Too many sheets in template, number of sheets : " + workbook.getNumberOfSheets());
		}
	}

	/**
	 * Vérifie si la ligne passer en arg est vide, la cellule sert pour les commentaires uniquement
	 * @param row
	 * @return True si la ligne est vide, sinon false
	 */
	public Boolean isLineEmpty(final Row row) {
		for (Cell cell : row) {
			if (!cellIsEmpty(cell)) {
				log.trace("Line was not empty : {}", row.getRowNum());
				return false;
			}
		}
		log.trace("Line was empty : {}", row.getRowNum());
		return true;
	}

	/**
	 * Vérifie si la cellule est vide
	 * @param cell
	 * @return
	 */
	public boolean cellIsEmpty(final Cell cell) {
		return cell != null && CellType.BLANK == cell.getCellType();
	}

	/**
	 * Permet de récupérer une cellule avec un numéro de colonne et de ligne. Si la cellule n'existe pas elle est créer.
	 * @param columnIndex
	 * @return
	 */
	public Cell getOrCreateCellFromPos(final Workbook workbook, final int columnIndex, final int rowIndex) {
		return getCellFromCellAddress(workbook, new CellAddress(rowIndex, columnIndex));
	}

	/**
	 * Prend une adresse excel, exemple A12, pour renvoyer la cellule du Workbook.
	 * @param cellAddress
	 * @return
	 */
	public Cell getOrCreateCellFromStringAddress(final Workbook workbook, final String cellAddress) {
		return getCellFromCellAddress(workbook, new CellAddress(cellAddress));
	}

	/**
	 * Permet de récupérer la ligne en dessous la ligne données. Attention aucune vérification n'est faite, savoir ce que l'on fait quand on utilise
	 * cette fonction.
	 * @param row
	 * @return
	 */
	public Row getRowBelow(final Workbook workbook, final Row row) {
		int targetRowNum = row.getRowNum() + 1;
		Row r = getWorkingSheet(workbook).getRow(targetRowNum);
		if (r == null) {
			r = getWorkingSheet(workbook).createRow(targetRowNum);
		}
		return r;
	}

	/**
	 * @param target
	 * @return
	 */
	public CellAddress getAddress(final Cell target) {
		return new CellAddress(target.getRowIndex(), target.getColumnIndex());
	}

	/**
	 * Permet de créer une list de cellule apparetenant à la même colonne
	 * @param columnIndex
	 * @param sheet
	 * @return
	 */
	public List<Cell> createListOfCellFromColomn(final Integer columnIndex, final Sheet sheet) {
		List<Cell> listCell = new ArrayList<>();
		for (Row r : sheet) {
			if (isAfterHeaders(r.getRowNum())) {
				for (Cell c : r) {
					if (c.getColumnIndex() == columnIndex) {
						listCell.add(c);
					}
				}
			}
		}
		return listCell;
	}

	public boolean isAfterHeaders(final int rowIndex) {
		return rowIndex >= indexLineEndHeaders;
	}

	public void groupColumnAndHide(final Sheet sheet, final Integer columnNumber) {
		groupColumn(sheet, columnNumber, columnNumber);
		sheet.setColumnGroupCollapsed(columnNumber, true);
	}

	private void groupColumn(final Sheet sheet, final Integer fromColumn, final Integer toColumn) {
		sheet.groupColumn(fromColumn, toColumn);
	}

	public void hideColumnAndUngroupIfNecessary(final Sheet sheet, final Integer columnIndex) {
		if (sheet.isColumnHidden(columnIndex)) {
			sheet.setColumnHidden(columnIndex, true);
			sheet.ungroupColumn(columnIndex, columnIndex);
		}
	}

	/**
	 * Permet de récuperer le Siren depuis le workbook
	 * @param workbook
	 * @return
	 */
	public String getSiret(final Workbook workbook) {
		Cell cell = getOrCreateCellFromStringAddress(workbook, siretCell);
		if (cell == null || cell.getStringCellValue() == null) {
			return null;
		} else {
			String value = cell.getStringCellValue();
			if (value.contains("null")) {
				String[] values = value.split(" ");
				if (values.length == 2) {
					return values[1];
				} else {
					log.error("Got a wrong number of result after split for getSiret, num result {}, expected 2, returning null", values.length);
				}
			} else {
				log.error("Cell didn't contain the expected string {} returning null", "null");
			}
			return null;
		}
	}

	/**
	 * Returns a cell from the workbook from the given CellAddress
	 * @param workbook
	 * @param ca
	 */
	public Cell getCellFromCellAddress(final Workbook workbook, final CellAddress ca) {
		Sheet sheet = getWorkingSheet(workbook);
		log.debug("Getting cell for address {}, row : {}, column : {}", ca.formatAsString(), ca.getRow(), ca.getColumn());
		Row row = sheet.getRow(ca.getRow());
		if (row == null) {
			row = sheet.createRow(ca.getRow());

		}
		Cell cell = row.getCell(ca.getColumn());
		if (cell == null) {
			cell = row.createCell(ca.getColumn());
		}
		return cell;
	}

	/**
	 * Finds an empty row in the workbook below the used rows passed as a param
	 * @param workbook
	 * @param usedRows
	 * @return
	 */
	public Integer findEmptyRowForDetailsData(final Workbook workbook, final List<Integer> usedRows) {
		Row row = null;
		Sheet sheet = getWorkingSheet(workbook);
		do {
			int resultRowIndex = 0;
			if (usedRows != null) {
				log.trace("usedRows contains {}", usedRows);
				while (usedRows.contains(resultRowIndex)) {
					resultRowIndex++;
				}
			} else {
				log.trace("usedRows was null");
			}
			log.trace("resultRowIndex is {}", resultRowIndex);
			row = CellUtil.getRow(resultRowIndex, sheet);
		} while (!isLineEmpty(row));
		return row.getRowNum();
	}
}
