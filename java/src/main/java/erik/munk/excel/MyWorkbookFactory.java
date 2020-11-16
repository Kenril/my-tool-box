/**
 *
 */
package erik.munk.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyWorkbookFactory {

	private static final String POINT = ".";

	private static final String UNDERSCORE = "_";

	private static final String REGEX_POUR_POINT = "\\.";

	/**
	 * Creation du fichier excel depuis une string encodé en Base64 repésentant le fichier template excel
	 * @param excelEncodedFileBase64, the template of the workbook to be created
	 * @return a file
	 * @throws IOException, when failing to create temp file
	 */
	public File createWorkbookFile(final String fileName, final String excelEncodedFileBase64) throws IOException {
		byte[] f = Base64.decodeBase64(excelEncodedFileBase64);
		File excel = new File(fileName);
		if (!excel.exists()) {
			excel = File.createTempFile(fileName.split(REGEX_POUR_POINT)[0] + UNDERSCORE, POINT + fileName.split(REGEX_POUR_POINT)[1]);
			log.info("Created temp template.xls file");
		}
		try (OutputStream os = new FileOutputStream(excel)) {
			os.write(f);
		}
		if (excel.isFile() && !excel.exists()) {
			log.warn("failed to create excel template");
		}
		return excel;
	}

	public Workbook createWorkbook(final String fileName, final String excelEncodedFileBase64) throws IOException {
		return WorkbookFactory.create(createWorkbookFile(fileName, excelEncodedFileBase64));
	}

	public Workbook createWorkbook(final File excelFile) throws IOException {
		return WorkbookFactory.create(excelFile);
	}

}
