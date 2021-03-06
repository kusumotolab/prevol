package jp.ac.osaka_u.ist.sdl.prevol.vectorwriter;

import jp.ac.osaka_u.ist.sdl.prevol.db.DBConnection;
import jp.ac.osaka_u.ist.sdl.prevol.utils.MessagePrinter;

/**
 * ベクトル対をCSVファイルに書き出すクラス
 * 
 * @author k-hotta
 * 
 */
public class VectorWriter {

	/**
	 * メインメソッド
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			final VectorWriterSettings settings = VectorWriterSettings
					.parseArgs(args);

			initialize(settings);

			AbstractWriter writer = null;

			if (settings.getMode() == VectorWriterMode.TRAINING) {
				if (settings.isTracking()) {
					writer = new TrackingAllColumnsTrainingSetWriter(settings);
				} else {
					writer = new AllColumnsTrainingSetWriter(settings);
				}
			} else if (settings.getMode() == VectorWriterMode.EVALUATION) {
				//
			} else if (settings.getMode() == VectorWriterMode.SINGLE_COLUMN_TRAINING) {
				if (settings.isTracking()) {
					writer = new TrackingSingleColumnTrainingSetWriter(settings);
				} else {
					writer = new SingleColumnTrainingSetWriter(settings);
				}
			} else if (settings.getMode() == VectorWriterMode.SINGLE_COLUMN_EVALUATION) {
				if (settings.isTracking()) {
					writer = new TrackingSingleColumnEvaluationSetWriter(
							settings);
				} else {
					writer = new SingleColumnEvaluationSetWriter(settings);
				}
			} else if (settings.getMode() == VectorWriterMode.RECURSIVE_EVALUATION) {
				writer = new RecursiveEvaluationSetWriter(settings);
			} else if (settings.getMode() == VectorWriterMode.TRAINING_WITH_ID) {
				writer = new AllColumnsTrainingSetWriterWithId(settings);
			}

			writer.write();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postprocess();
		}
	}

	/**
	 * 初期設定を行う
	 * 
	 * @param settings
	 * @throws Exception
	 */
	private static void initialize(final VectorWriterSettings settings)
			throws Exception {
		// 出力レベルを設定
		MessagePrinter.setMode(settings.getPrintMode());

		MessagePrinter.stronglyPrintln("operations start");
		MessagePrinter.stronglyPrintln();

		// データベースとのコネクションを生成
		MessagePrinter.stronglyPrintln("initializing db ... ");
		DBConnection.createInstance(settings.getDbPath());
		MessagePrinter.stronglyPrintln("\tOK");
		MessagePrinter.stronglyPrintln();
	}

	/**
	 * 後処理を行う
	 */
	private static void postprocess() {
		if (DBConnection.getInstance() != null) {
			DBConnection.getInstance().close();
		}
	}

}
