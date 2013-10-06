package com.deeep.sod2.io;

/**
 * Name: SaveHandler
 * Pack: com.deeep.sod2.io
 * User: andreaskruhlmann
 * Date: 10/4/13
 */
public class SaveHandler {
}

/**

 package com.kruhlmann.towerdefense.io;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Scanner;

 import com.kruhlmann.towerdefense.Component;
 import com.kruhlmann.towerdefense.Direction;
 import com.kruhlmann.towerdefense.ai.Monster;
 import com.kruhlmann.towerdefense.ai.monsters.DragonBaby;
 import com.kruhlmann.towerdefense.ai.monsters.FrostWarrior;
 import com.kruhlmann.towerdefense.ai.monsters.Outlaw;
 import com.kruhlmann.towerdefense.ai.monsters.Scorpion;
 import com.kruhlmann.towerdefense.ai.monsters.Skeleton;
 import com.kruhlmann.towerdefense.ai.monsters.Slime;
 import com.kruhlmann.towerdefense.ai.monsters.Spider;
 import com.kruhlmann.towerdefense.ai.monsters.Zombie;
 import com.kruhlmann.towerdefense.ai.monsters.DarkPriest;
 import com.kruhlmann.towerdefense.level.Level;

 public class Save {

 public Level load(String path){
 Level currentLevel = new Level();
 if(path == null) {
 currentLevel = null;
 System.out.println("[WARNING] Loading blank save");
 return currentLevel;
 }

 try {
 File file = new File(path);
 Scanner scanner = new Scanner(file);
 currentLevel.monsterStartX = scanner.nextInt();
 currentLevel.monsterStartY = scanner.nextInt();
 Component.numberOfWaves = scanner.nextInt();
 int dir = scanner.nextInt();
 if(dir == 0) currentLevel.startDirection = Direction.NORTH;
 else if(dir == 1) currentLevel.startDirection = Direction.EAST;
 else if(dir == 2) currentLevel.startDirection = Direction.SOUTH;
 else if(dir == 3) currentLevel.startDirection = Direction.WEST;
 for(int y = 0; y < currentLevel.heightInTiles; y++){
 for(int x = 0; x < currentLevel.widthInTiles; x++){
 int i = scanner.nextInt();
 currentLevel.tiles[x + y * currentLevel.widthInTiles].groundId = i;
 }
 }
 int ii = 5;
 while(ii != 4){
 ii = scanner.nextInt();
 if(ii != 4){
 currentLevel.moveOrders.add(ii);
 }
 }

 boolean notFinished;
 for(int i = 0; i < Component.numberOfWaves; i ++){
 notFinished = true;
 while(notFinished){
 int iii = scanner.nextInt();
 if(iii != 0) {
 if(iii == 1)  currentLevel.wave.add(new Zombie(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 2)  currentLevel.wave.add(new Zombie(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 3)  currentLevel.wave.add(new Zombie(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 4)  currentLevel.wave.add(new Skeleton(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 5)  currentLevel.wave.add(new Skeleton(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 6)  currentLevel.wave.add(new Skeleton(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 7)  currentLevel.wave.add(new Outlaw(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 8)  currentLevel.wave.add(new Outlaw(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 9)  currentLevel.wave.add(new Outlaw(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 10)  currentLevel.wave.add(new FrostWarrior(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 11)  currentLevel.wave.add(new FrostWarrior(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 12)  currentLevel.wave.add(new FrostWarrior(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 13)  currentLevel.wave.add(new Slime(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 14)  currentLevel.wave.add(new Slime(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 15)  currentLevel.wave.add(new Slime(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 16)  currentLevel.wave.add(new Spider(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 17)  currentLevel.wave.add(new Spider(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 18)  currentLevel.wave.add(new Spider(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 19)  currentLevel.wave.add(new DragonBaby(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 20)  currentLevel.wave.add(new DragonBaby(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 21)  currentLevel.wave.add(new DragonBaby(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 22)  currentLevel.wave.add(new Scorpion(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 23)  currentLevel.wave.add(new Scorpion(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 24)  currentLevel.wave.add(new Scorpion(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 if(iii == 25)  currentLevel.wave.add(new DarkPriest(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 1));
 if(iii == 26)  currentLevel.wave.add(new DarkPriest(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 2));
 if(iii == 27)  currentLevel.wave.add(new DarkPriest(currentLevel.monsterStartX, currentLevel.monsterStartY, currentLevel.startDirection, 3));
 int d = scanner.nextInt();
 currentLevel.spawnDelays.add(d);
 }else notFinished = false;
 }
 currentLevel.waves.add(new ArrayList<Monster>(currentLevel.wave));
 currentLevel.wave.clear();
 }
 int dollaz = scanner.nextInt();
 if(Component.difficulty == 1) currentLevel.funds = dollaz;
 else currentLevel.funds = (int) (Math.floor(dollaz*(1-(Component.difficulty/10.))));

 for(Monster monster: currentLevel.wave)
 monster.moveOrders = currentLevel.moveOrders;

 scanner.close();
 System.out.println("[INFO] Loaded level " + path);
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 }
 return currentLevel;
 }

 }

 */