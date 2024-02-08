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

    // im not sure if its right or no 
    // but iguess we're missing getters and setters

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



    public void takeDamage(int damage){
        // methode pour handle le damage taken by the hero  
        this.health -= damage;
        if(this.health < 0){
            this.health = 0;
        }
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
    
    public boolean fight(int numEnemies) {
        // the fight action of the Hero
        int totalDamage = numEnemies * this.attackPoints * 2; // Car c'est double damage
        this.damageInflicted += totalDamage;
        takeDamage(totalDamage);
        return isAlive();
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

    public boolean isAlive(){
        // pour checker if the hero is alive ou non
        return this.health > 0;
    }


}

