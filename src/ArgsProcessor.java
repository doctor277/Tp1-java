public class ArgsProcessor {
    // Méthode pour traiter les actions du héros basées sur les arguments d'entrée
    public static void process(String[] args) {
        // Créer un tableau de chaînes de caractères à partir de la première entrée
        String[] phrase = makePhrase(args[0]);

        // Extraire le nom, la santé et les points d'attaque du héros des arguments
        String heroName = phrase[0];
        int heroHealth = Integer.parseInt(phrase[1]);
        int heroAttackPoints = Integer.parseInt(phrase[2]);

        // Déclarer une variable pour le héros
        Hero hero;

        // Utiliser le premier caractère du nom du héros pour déterminer son type
        switch (heroName.charAt(0)) {
            case 'A' -> hero = new AttackHero(heroHealth, heroAttackPoints); // Héros de type Attaque
            case 'D' -> hero = new DefenseHero(heroHealth, heroAttackPoints); // Héros de type Défense
            default -> hero = new BalancedHero(heroHealth, heroAttackPoints); // Héros de type Équilibré
        }

        // Variable pour suivre si le héros a survécu
        boolean survived = true;

        // Parcourir les actions restantes et les exécuter
        for (int i = 3; i < phrase.length; i++) {
            survived = doAction(phrase[i], hero); // Exécuter une action et mettre à jour le statut de survie
            if (!survived)
                break; // Arrêter si le héros meurt
        }

        // Créer la sortie basée sur les actions effectuées et le statut de survie
        String output = makeOutput(heroName, hero, survived);
        System.out.println(output); // Afficher le résultat
    }


    private static String[] makePhrase(String args) {
        return args.trim().split(",");
    }

    // méthode qui prend la partie de la phrase qui décrit l'action et le héros, puis effectue l'action correspondante
    // retourne true si le joueur survit à l'action, false sinon
    private static boolean doAction(String action, Hero hero) {
        // ici, on transforme le String action en un tableau de String, en séparant les mots par des espaces
        String[] phrase = action.trim().split(" ");
        // le type d'action est déterminé par le premier mot de la phrase
        boolean survived = true;
        switch (phrase[0]) {
            case "fought":
                survived = hero.fight(Integer.parseInt(phrase[1]));
                break;
            case "rested":
                hero.rest();
                break;
            case "healed":
                hero.heal(Integer.parseInt(phrase[1]));
                break;
            case "trained":
                hero.train(Integer.parseInt(phrase[3]));
                break;
        }
        return survived;
    }

    private static String makeOutput(String heroName, Hero hero, boolean survived) {
        String out;
        if (survived) {
            out = "In his quest, " + heroName + " beat " +
                    hero.nbrOfEnemyDefeated + " ennemies, attained level " +
                    hero.level + " and survived with " + hero.health + " HP!";
        }
        else {
            out = "In his quest, " + heroName + " died after beating " +
                    hero.nbrOfEnemyDefeated + " ennemies and attaining level " +
                    hero.level + "!";
        }
        return out;
    }
}

