public class Hero {
    private String nom;
    private int health;
    private int maxHealth;
    private int level;
    private int experience;
    private int attackPoints;
    private int damageInflicted;

    public Hero(String nom, int health, int maxHealth, int level, int experience, int attackPoints, int damageInflicted){
        this.nom = nom;
        this.health = health;
        this.maxHealth = maxHealth;
        this.level = level;
        this.experience = experience;
        this.attackPoints = attackPoints;
        this.damageInflicted = damageInflicted;
    }

    public String getHero() {
    char premiereLettre = this.nom.charAt(0);
    if (premiereLettre == 'A') {
        return "Attaque";
    } else if (premiereLettre == 'D') {
        return "Défense";
    } else {
        return "Équilibre";}
    }

    public int getHealth(){
        return this.health;
    }

    public void setHealth(int newHealth){
        this.health = newHealth;
    }
     
    public int getAttackPoints(){
        return this.attackPoints;
    }
    
    public void setAttackPoints(int newAttackPoints){
        this.attackPoints = newAttackPoints;
    }

    public boolean isAlive(){
        // pour checker if the hero is alive ou non
        return this.health > 0;
    }

    public void takeDamage(int damage){
        // methode pour handle le damage taken by the hero  
        this.health -= damage;
        if(this.health < 0){
            this.health = 0;
        }
    }

    

    public boolean fight(int numEnemies) {
        int totalDamage;
        String type = getHero();
    
        // Si le héros est de type attaque
        if (type.equals("Attaque")) {
            totalDamage = numEnemies * getAttackPoints() * 2;
            this.damageInflicted += totalDamage;
            takeDamage(totalDamage * 2); // Reçoit le double des dégâts
        } 
        // Si le héros est de type défense
        else if (type.equals("Défense")) {
            totalDamage = numEnemies * getAttackPoints() / 2;
            this.damageInflicted += totalDamage;
            takeDamage(totalDamage / 2); // Ne reçoit que la moitié des dégâts
        } 
        // Si le héros est de type équilibre ou autre
        else {
            totalDamage = numEnemies * getAttackPoints();
            this.damageInflicted += totalDamage;
            takeDamage(totalDamage); // Dégâts normaux
        }
        return isAlive();
    }


    public void levelUp(){
        // methode pour le level up
        if(this.level < 99){
            this.level++;                   
            this.maxHealth += 12;           //  augmenter le maximum health 
            this.health = this.maxHealth;   // restore le health on level up
            this.attackPoints += 6;         // augmenter le attack points
        }
    }


    public void gainExp(int experiencePoints){
        // methode pour gagner des experiences 
        // exp_requis = 50 + niveau_voulu * 20 * (1.1^niveau_voulu)
        this.experience += experiencePoints;
        
        // check si le hero peut se level up
        while( this.experience >= calculerExperience()){
            levelUp();
        }
    }

    public int calculerExperience(){
        // methode pour pacluler l'experience pour atteindre le next level
        // exp_requis = 50 + niveau_voulu * 20 * (1.1^niveau_voulu)
        return 50 + this.level * 20 * (int) Math.pow(1.1, this.level);   
    }

    public boolean rest() {
        // the rest action of the Hero
        this.health = this.maxHealth; // restore health
        return isAlive();
    }

    public boolean heal(int healthPoints){
        // the heal action of the Hero
        this.health += healthPoints;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        return isAlive();
    }

    public boolean train(int trainingPoints){
        // handle the train action of the Hero
        this.attackPoints += trainingPoints;
        return isAlive();
    }
