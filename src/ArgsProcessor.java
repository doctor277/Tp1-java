public class ArgsProcessor2 {
    public static void process(String[] args) {
        String[] phrase = makePhrase(args[0]);

        String nom = phrase[0];
        int health = Integer.parseInt(phrase[1]);
        int attackPoints = Integer.parseInt(phrase[2]);

        Hero hero = new Hero(nom, health, health, 1, 0, attackPoints);

        // TODO : Handle the phrase and output the result
    }

    //
    private static String[] makePhrase(String args) {
        return args.trim().split(",");
    }

    // méthode qui prend la partie de la phrase qui décrit l'action et le héros,
    // puis effectue l'action correspondante
    // retourne true si le joueur survit à l'action, false sinon
    private static boolean doAction(String action, Hero hero) {
        // ici, on transforme le String action en un tableau de String, en séparant les
        // mots par des espaces
        String[] phrase = action.trim().split(" ");
        // le type d'action est déterminé par le premier mot de la phrase
        switch (phrase[0]) {
            case "fought":
                // TODO : Handle the fight
                br eak;
            case "rested":
                // TODO : Handle the resting
                break;
            case "healed":
                // TODO : Handle the healing
                break;
            case "trained":
                // TODO : Handle the training
                break;
        }
        return true;
    }

}
