# FasterBridge
This is a spigot plugin for fastbridging.
It's under development.
You can access it under the dev branch.

<details>
<summary>Versions</summary>
<br>
    
## Versions
|Version|Working|
| ----------- | ----------------------- |
| 1.8 | ✓ |
| 1.9 | ? |
| 1.10 | ? |
| 1.11 | ? |
| 1.12 | ? |
| 1.13 | ? |
| 1.14 | ? |
| 1.15 | ? |
| 1.16 | ✓ |
| 1.17 | ? |
</details>


## Quick tutorial:

[![](https://yt-embed.herokuapp.com/embed?v=pmLcsv-lLGw)](https://youtu.be/pmLcsv-lLGw)

## Documentations
<details>
<summary>Permissions</summary>
<br>

## Permissions
### SubCommands

| SubCommand  |     Permission	        |
| ----------- | ----------------------- |
| test	 	  | fastbridge.test         |
| create      | fastbridge.create       |
| func        | fastbridge.func         |
| givegadget  | fastbridge.givegadget   |
| editor      | fastbridge.editor       |
| setloc      | fastbridge.setloc       |
| setblock    | fastbridge.setblock     |
| blocks      | fastbridge.blocks       |

### Gadgets

|       Gadget        |         Permission	            |
| ------------------- | ------------------------------- |
| arenaEditorGadget   | fastbridge.arenaEditorGadget    |
| arenaLeaveGadget    | fastbridge.arenaLeaveGadget     |
| arenaSelectorGadget | fastbridge.arenaSelectorGadget  |
| arenaRestartGadget  | fastbridge.arenaRestartGadget   |
</details>
<details>
<summary>Events</summary>
<br>

# Custom events

## Arena
PlayerJoinArenaEvent
```java
@EventHandler
public void onPlayerJoinArena(PlayerJoinArenaEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

PlayerLeaveArenaEvent
```java
@EventHandler
public void onPlayerLeaveArena(PlayerLeaveArenaEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

ArenaCreateEvent
```java
@EventHandler
public void onArenaCreated(ArenaCreateEvent event){
    event.getArenaName();           //  String
    event.getMaxPlayer();           //  int
    event.isActive();               //  boolean
    event.getLocations();           //  Location[]
    event.getDeathZoneHorizontal(); //  int
    event.getDeathZoneVertical();   //  int
    event.getDirection();           //  Direction
        }
```

ArenaDeleteEvent
```java
@EventHandler
public void onArenaDelete(ArenaDeleteEvent event){
    event.getArenaName();      //  String
        }
```

## Editor
PlayerJoinEditorEvent
```java
@EventHandler
public void onPlayerJoinEditor(PlayerJoinArenaEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

PlayerLeaveEditorEvent
```java
@EventHandler
public void onPlayerLeaveEditor(PlayerLeaveEditorEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

## Game

PlayerArenaResetEvent
```java
@EventHandler
public void onPlayerResetArena(PlayerArenaResetEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

PlayerDeathEvent
```java
@EventHandler
public void onPlayerDeath(PlayerDeathEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
        }
```

PlayerNewRecordEvent
```java
@EventHandler
public void onPlayerNewRecord(PlayerNewRecordEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
    event.getRecord()       //  double    
        }
```

PlayerWinEvent
```java
@EventHandler
public void onPlayerWin(PlayerWinEvent event){
    event.getPlayer();      //  Player
    event.getArenaName();   //  String
    event.getPlayerClass(); //  PlayerClass
    event.getArenaClass();  //  ArenaClass
    event.getRecord()       //  double    
        }
```

## Player

ChangeBlockEvent
```java
@EventHandler
public void onPlayerBlockChange(ChangeBlockEvent event){
    event.getPlayer();      //  Player
    event.getPlayerClass(); //  PlayerClass
    event.getMaterial();    //  Material
    event.getDisplayName(): //  String    
        }
```

## Other

SubCommandEvent
```java
@EventHandler
public void onSubCommand(PlayerWinEvent event){
    event.getPlayer();      //  Player
    event.getCommand()      //  String    
        }
```

GadgetUseEvent
```java
@EventHandler
public void onGadgetUse(GadgetUseEvent event){
    event.getPlayer();      //  Player
    event.getGadgetName()   //  String   
    event.getGadget()       //  GadgetAbstract    
        }
```

ButtonClickEvent
```java
@EventHandler
public void onButtonClick(ButtonClickEvent event){
    event.getPlayer();      //  Player
    event.getSlot();        //  int
    event.getClicked();     //  ItemStack
    event.getInventory();   //  Inventory
    event.getButton();      //  ButtonInterface    
        }
```
</details>
