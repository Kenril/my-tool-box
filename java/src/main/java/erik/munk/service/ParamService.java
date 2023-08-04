package erik.munk.service;

public class ParamService {

	public String getString(String keyEdcExcelCellWithSiret, String defaultValue) {
		return "";
	}

	public String getString(String keyEdcExcelCellWithSiret) {
		return getString(keyEdcExcelCellWithSiret, null);
	}

	public Integer getInteger(String keyEdcExcelIndexLineEndHeaders, Integer defaultValue) {
		return 1;
	}

	public Integer getInteger(String keyEdcExcelIndexLineEndHeaders) {
		return getInteger(keyEdcExcelIndexLineEndHeaders, null);
	}
}
