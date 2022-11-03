import java.awt.*;
import java.awt.event.InputEvent;

public class AutoClicker {
    Robot r;
    int button;

    /*
     * Coordonnées des clics
     * 0 = click window
     * 1-2 = buy all batiment
     * 3-4-8 = buy all amelioration du tiers en cours
     * 5-6-7 = elevation
     */
    String[] coordonnees = {
            "100-100", // 0 - Click fenetre
            "2528-1227", // 1 - click menu batimernt
            "2376-1389", // 2 - click bouton acheter tout
            "2490-1083", // 3 - click menu amelioration
            "2347-478", // 4 - Bouton amelioration acheter tout tier 11
            "2496-936", // 5 - click menu elevation
            "2335-401", // 6 - click bouton elevation
            "2009-1064", // 7 - click confirmation elevation
            "2328-402", // 8 - Bouton amelioration acheter tout tier 1 - 10
            "2318-794", // 9 - Bouton amelioration tier 12
            "155-1334", // 10 - Grappin
            "364-1302" // 11 - Sherikun
    };

    public AutoClicker() {
        try {
            this.r = new Robot();
            button = InputEvent.BUTTON1_DOWN_MASK;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Click sur la fenêtre pour mettre un bon coup de katana
     */
    public void clickFenetre() {
        int[] coord = this.getLocation(0);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(false);
    }

    /**
     * On s'eleve toujours plus haut !!
     */
    public void clickElevation() {
        System.out.println("Click menu elevation");
        int[] coord = this.getLocation(5);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);

        System.out.println("Click elevation");
        coord = this.getLocation(6);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);

        System.out.println("Click confirmation elevation");
        coord = this.getLocation(7);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);
    }

    /**
     * Click sur le bouton acheter tout les bâtiments
     */
    public void clickBuyAllBatiment() {
        System.out.println("Click Batiment");
        int[] coord = this.getLocation(1);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);
        System.out.println("Click Acheter tout");
        coord = this.getLocation(2);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);
    }

    /**
     * Click sur le bouton acheter tout des ameliorations
     */
    public void clickBuyAllAmelioration() {
        System.out.println("Click Amelioration");
        int[] coord = this.getLocation(3);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);

        coord = this.getLocation(4);
        if (this.isGreen(coord[0], coord[1])) {
            System.out.println("Click Acheter tout tier 11");
            this.r.mouseMove(coord[0], coord[1]);
            this.click(true);
        }

        coord = this.getLocation(9);
        if (this.isGreen(coord[0], coord[1])) {
            System.out.println("Click Acheter tout tier 12");
            this.r.mouseMove(coord[0], coord[1]);
            this.click(true);
        }

        coord = this.getLocation(8);
        if (this.isGreen(coord[0], coord[1])) {
            System.out.println("Click Acheter tout tier 1-10");
            this.r.mouseMove(coord[0], coord[1]);
            this.click(true);
        }

    }

    /**
     * Verifie que le pixel sous le curseur est vert
     * 
     * @param x coordonnées x du pixel
     * @param y coordonnées y du pixel
     * @return true si le pixel est vert, false sinon
     */
    private Boolean isGreen(int x, int y) {
        Color c = this.r.getPixelColor(x, y);
        if (c.getRed() == 12 && c.getGreen() == 178 && c.getBlue() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Check si la souris a bougé
     * Si oui, on arrete le script
     * 
     * @return void
     */
    public void checkIfMouseIsMoving() {
        boolean isMoving = false;

        Point p = MouseInfo.getPointerInfo().getLocation();

        for (String string : coordonnees) {
            if (string.equals(p.x + "-" + p.y)) {
                isMoving = true;
            }
        }

        if (!isMoving) {
            System.out.println("La souris a bougé, programme arrêté");
            System.exit(0);
        }

    }

    /**
     * Ca clique !!
     * 
     * @param pause true si on veut une pause de 400ms aprés le clic
     * @return void
     */
    private void click(Boolean pause) {
        try {
            this.r.mousePress(button);
            Thread.sleep(400);
            this.r.mouseRelease(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // On est obligé de faire une pause quand on clique dans un menu sinon le click
        // n'est pas pris en compte
        if (pause) {
            this.pause();
        }
    }

    /**
     * On lance le grappin
     */
    public void grappin() {
        System.out.println("Click grappin");
        int[] coord = this.getLocation(10);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);
    }

    /**
     * On lance les sherikun
     */
    public void sherikun() {
        System.out.println("Click sherikun");
        int[] coord = this.getLocation(11);
        this.r.mouseMove(coord[0], coord[1]);
        this.click(true);
    }

    /**
     * Petite pause ?
     * 
     * @return void
     */
    public void pause() {
        try {
            Thread.sleep(400);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Récupère les coordonnées d'un index
     * 
     * @param index index du tableau de coordonnées
     * @return tableau de coordonnées
     */
    private int[] getLocation(int index) {
        String location = this.coordonnees[index];
        String[] parts = location.split("-");
        int[] coords = new int[2];
        coords[0] = Integer.parseInt(parts[0]);
        coords[1] = Integer.parseInt(parts[1]);
        return coords;
    }

}