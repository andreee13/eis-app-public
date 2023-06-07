package it.unipd.dei.eis;

import it.unipd.dei.eis.presentation.Bootstrapper;

/**
 * The App class is the main class of the application.
 */
public class App {

    /**
     * The entry point of the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Bootstrapper(args).launch();
    }
}