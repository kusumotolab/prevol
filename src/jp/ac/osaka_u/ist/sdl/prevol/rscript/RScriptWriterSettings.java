package jp.ac.osaka_u.ist.sdl.prevol.rscript;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

public class RScriptWriterSettings {

	private final String trainingFile;

	private final String evaluationFile;

	private final String correctFile;

	private final String predictedFile;

	private final String diffFile;

	private final String outputFile;

	private final RScriptWriterMode mode;

	private RScriptWriterSettings(final String trainingFile,
			final String evaluationFile, final String correctFile,
			final String predictedFile, final String diffFile,
			final String outputFile, final RScriptWriterMode mode) {
		this.trainingFile = trainingFile;
		this.evaluationFile = evaluationFile;
		this.correctFile = correctFile;
		this.predictedFile = predictedFile;
		this.diffFile = diffFile;
		this.outputFile = outputFile;
		this.mode = mode;
	}

	public String getTrainingFile() {
		return trainingFile;
	}

	public String getEvaluationFile() {
		return evaluationFile;
	}

	public String getCorrectFile() {
		return correctFile;
	}

	public String getPredictedFile() {
		return predictedFile;
	}

	public String getDiffFile() {
		return diffFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public RScriptWriterMode getMode() {
		return mode;
	}

	public static RScriptWriterSettings parseArgs(final String[] args)
			throws Exception {
		final Options options = defineOptions();

		final CommandLineParser parser = new PosixParser();
		final CommandLine cmd = parser.parse(options, args);

		final String trainingFile = cmd.getOptionValue("t");
		final String evaluationFile = cmd.getOptionValue("e");
		final String correctFile = cmd.getOptionValue("c");
		final String predictedFile = cmd.getOptionValue("p");
		final String diffFile = cmd.getOptionValue("d");
		final String outputFile = cmd.getOptionValue("o");

		RScriptWriterMode mode = RScriptWriterMode.LM;
		if (cmd.hasOption("G")) {
			mode = RScriptWriterMode.GAM;
		}

		return new RScriptWriterSettings(trainingFile, evaluationFile,
				correctFile, predictedFile, diffFile, outputFile, mode);
	}

	private static Options defineOptions() {
		final Options options = new Options();

		{
			final Option d = new Option("d", "diff", true, "diff file");
			d.setArgs(1);
			d.setRequired(true);
			options.addOption(d);
		}

		{
			final Option c = new Option("c", "correct", true, "diff file");
			c.setArgs(1);
			c.setRequired(true);
			options.addOption(c);
		}

		{
			final Option p = new Option("p", "predicted", true,
					"predicted file");
			p.setArgs(1);
			p.setRequired(true);
			options.addOption(p);
		}

		{
			final Option t = new Option("t", "training", true, "training file");
			t.setArgs(1);
			t.setRequired(true);
			options.addOption(t);
		}

		{
			final Option e = new Option("e", "evaluation", true,
					"evaluation file");
			e.setArgs(1);
			e.setRequired(true);
			options.addOption(e);
		}

		{
			final Option o = new Option("o", "output", true, "output file");
			o.setArgs(1);
			o.setRequired(true);
			options.addOption(o);
		}

		{
			final Option G = new Option("G", "GAM", false, "USE GAM");
			G.setRequired(false);
			options.addOption(G);
		}

		return options;
	}

}
