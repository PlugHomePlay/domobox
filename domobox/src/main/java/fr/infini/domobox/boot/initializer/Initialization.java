package fr.infini.domobox.boot.initializer;

import javax.servlet.http.HttpServlet;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Initialization extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() {
		this.splashScreen();
		RedisInitializer.INSTANCE.compute();
	}

	private void splashScreen() {
		log.info("                           (   )");
		log.info("                          (    )");
		log.info("                           (    )");
		log.info("                          (    )");
		log.info("                            )  )");
		log.info("                           (  (                  /\\");
		log.info("                            (_)                 /  \\  /\\");
		log.info("                    ________[_]________      /\\/    \\/  \\");
		log.info("           /\\      /\\        ______    \\    /   /\\/\\  /\\/\\");
		log.info("          /  \\    //_\\       \\    /\\    \\  /\\/\\/    \\/    \\");
		log.info("   /\\    / /\\/\\  //___\\       \\__/  \\    \\/");
		log.info("  /  \\  /\\/    \\//_____\\       \\ |[]|     \\");
		log.info(" /\\/\\/\\/       //_______\\       \\|__|      \\");
		log.info("/      \\      /XXXXXXXXXX\\                  \\");
		log.info("        \\    /_I_II  I__I_\\__________________\\");
		log.info("               I_I|  I__I_____[]_|_[]_____I");
		log.info("               I_II  I__I_____[]_|_[]_____I");
		log.info("               I II__I  I     XXXXXXX     I");
		log.info("            ~~~~~\"   \"~~~~~~~~~~~~~~~~~~~~~~~~");
		log.info("=====================================================================");
		log.info("||                     Domobox V1.0                                ||");
		log.info("=====================================================================");
		log.info("");
	}
}
