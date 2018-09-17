import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class test {
	static Logger logger = LoggerFactory.getLogger(test.class);

	public static void main(String[] args) {

		logger.debug("This is debug message");
		logger.info("This is info message");
		logger.warn("This is warn message");
		// logger.fatal("This is fatal message");
		logger.error("This is error message");
		System.out.println("Logic executed successfully....");
	}
}
