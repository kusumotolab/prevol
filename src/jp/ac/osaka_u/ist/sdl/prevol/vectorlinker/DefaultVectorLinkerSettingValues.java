package jp.ac.osaka_u.ist.sdl.prevol.vectorlinker;

import jp.ac.osaka_u.ist.sdl.prevol.utils.MessagePrinterMode;

/**
 * VectorLinker のデフォルトの設定を保持するインターフェース
 * 
 * @author k-hotta
 * 
 */
interface DefaultVectorLinkerSettingValues {

	static final String DEFAULT_ADDITIONAL_PATH = null;

	static final int DEFAULT_THREADS = 1;

	static final String DEFAULT_USERNAME = null;

	static final String DEFAULT_PASSWD = null;
	
	static final long DEFAULT_START_REVISION = 1;

	static final long DEFAULT_END_REVISION = Long.MAX_VALUE;

	static final double DEFAULT_SIMILARITY_THRESHOLD = 0.0;

	static final MessagePrinterMode DEFAULT_PRINT_MODE = MessagePrinterMode.VERBOSE;

}
