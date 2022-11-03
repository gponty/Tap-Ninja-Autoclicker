public class Main {
    public static void main(String[] args) {

        AutoClicker ac = new AutoClicker();
        long lastUpdate = System.currentTimeMillis();
        long lastUpdateElevation = System.currentTimeMillis();

        while (true) {

            // On click tout le temps pour simuler un coup de katana
            ac.clickFenetre();

            // On lance le grappin
            ac.grappin();

            // On lance les sherikun
            ac.sherikun();

            // Toutes les 2 secondes on upgrade Batiments et ameliorations
            if (System.currentTimeMillis() - lastUpdate > 2000) {
                ac.clickBuyAllBatiment();
                ac.clickBuyAllAmelioration();
                lastUpdate = System.currentTimeMillis();
            }

            // Toutes les 5 minutes on s'eleve
            if (System.currentTimeMillis() - lastUpdateElevation > (5000 * 12 * 5)) {
                ac.clickElevation();
                lastUpdateElevation = System.currentTimeMillis();
            }

            // Si la souris a bougé on arrête le script
            ac.checkIfMouseIsMoving();

        }

    }

}
