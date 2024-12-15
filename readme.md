# CW2024 Project: Level-Based Game with Enhanced Features

This task is a stage-primarily based game evolved as a part of CW2024 coursework. The recreation consists of more than one levels with unique challenges, which include a boss fight, shield mechanics, health control, and further UI functions.

---

## GitHub

Provide the link to the GitHub repository here.

---

## Compilation Instructions

1. **Setup**:
    - Clone the repository.
    - Open the task in an IDE like IntelliJ IDEA.
    - Ensure all dependencies (e.G., JavaFX) are well configured.

2. **Build and Run**:
    - Build the project using your IDE's construct equipment.
    - Run the `Main` magnificence to start the game.

Three. **Play**:
- Use the start menu to start the game.
- Navigate via levels and defeat the boss whilst maintaining track of your score and health.

---

## Implemented and Working Properly

### Gameplay Features
- **Level Progression**: The recreation includes more than one levels with increasing difficulty.
- **Boss Fight**: A difficult boss with health and shield mechanics is covered in Level Two.
- **Projectile Mechanics**: Both the participant and enemies can fire projectiles.
- **Shield Mechanic**: The boss can spark off a shield to block incoming harm.

### User Interface Features
- **Health Display**: Displays the participant's health and the boss's health dynamically.
- **Score Display**: Tracks and presentations the participant's score throughout the game.
- **Start Menu**: A visually engaging start menu that permits customers to:
    - start the game.
    - Exit the application at once.
    - Set the tone for the game revel in with an expert format.
- **End Menus**:
    - **Win Screen**: Displays a congratulatory message, final score, and options to replay or exit the game.
    - **Game Over Screen**: Displays a recreation-over message, very last score, and similar replay or go out options.
- **Shield Display**: A visual indicator is displayed while the boss's shield is lively, replacing the boss's health display with "Shield On."

---

## Implemented however Not Working Properly

None. All functions have been implemented and are functioning as expected.

---

## Features Not Implemented

- Additional levels beyond Level Two.
- Parameterization for shield period and activation chance.
- Advanced sound consequences and history tune.
- Animations for smoother transitions and visual effects.

---

## New Java Classes

1. **`ScoreDisplay`**:
    - **Purpose**: Displays the player's score dynamically at the display screen.
    - **Location**: `com.Instance.Demo` package deal.

2. **`BossHealthDisplay`**:
    - **Purpose**: Handles the UI for displaying the boss's health or "Shield On" reputation.
    - **Location**: `com.Instance.Demo` package deal.

---

## Modified Java Classes

1. **`Main`**:
    - **Modifications**: Added logic to initialize and cope with transitions among start menu, levels, and quit screens.

2. **`Controller`**:
    - **Modifications**: Updated to handle game flow transitions together with the start menu, game levels, and stop menus.

Three. **`LevelView`**:
- **Modifications**: Integrated `ShieldImage` and ensured it is dynamically toggled at some stage in gameplay. Also delivered strategies for start and quit menu visuals.

Four. **`LevelViewLevelTwo`**:
- **Modifications**: Removed redundant `BossHealthDisplay` and refactored to rely upon `LevelView` for regular UI updates.

5. **`Boss`**:
    - **Modifications**: Updated common logic to block harm while the shield is lively. Added strategies to set off and deactivate the shield.

6. **`LevelTwo`**:
    - **Modifications**: Integrated shield mechanics and ensured proper transitions among shielded and unshielded states. Fixed the problem with repeated LevelTwo initialization.

7. **`ShieldImage`**:
    - **Modifications**: Adjusted visibility toggling and introduced integration with `LevelView` for better gameplay clarity.

Eight. **`LevelOne` and `LevelParent`**:
- **Modifications**: Refactored shared common logic and added guide for higher UI and gameplay transitions.

---

## Unexpected Problems

### Gameplay Issues
1. **LevelTwo Re-initialization Loop**:
    - **Problem**: Loading `LevelTwo` multiple times caused JVM memory exhaustion.
    - **Resolution**: Added situations to prevent a couple of concurrent initializations of the equal degree.

2. **Boss Health Not Updating**:
    - **Problem**: The boss's health turned into not dynamically updating at the UI for the duration of the game.
    - **Resolution**: Used the `BossHealthDisplay` instance from `LevelView` completely, ensuring it updates effectively on health changes.

3. **Shield Image Not Displayed**:
    - **Problem**: The shield photograph turned into initialized however not displayed all through gameplay.
    - **Resolution**: Ensured the `ShieldImage` became delivered to the `LevelView` root group and its visibility toggled dynamically.

4. **No Start or End Menus**:
    - **Problem**: The recreation lacked menus for starting the game or displaying consequences after final touch.
    - **Resolution**: Added an expert start menu and superior cease menus (win and game-over screens).

5. **Boss Taking Damage While Shielded**:
   - **Problem**: The boss took damage even if the shield turned into active.
   - **Resolution**: Updated the `Boss` class common logic to dam damage when the shield is active.

### UI Issues
1. **Redundant `BossHealthDisplay` Instances**:
    - **Problem**: Two separate times of `BossHealthDisplay` induced inconsistencies in health updates.
    - **Resolution**: Consolidated `BossHealthDisplay` into `LevelView` and removed replica initialization from `LevelViewLevelTwo`.

2. **Score and Health Overlap**:
    - **Problem**: UI elements overlapped, making it hard to examine both the score and health.
    - **Resolution**: Adjusted the positions of UI components to ensure clear visibility.

---

## Enhancements

1. **Start Menu**:
    - Added a sophisticated start menu with alternatives to start or go out the game.
    - Enhanced the general consumer experience via developing an intuitive entry factor.

2. **Win and Game Over Screens**:
    - Added replay and go out alternatives to both menus.
    - Displayed the very last score prominently to provide closure to the gameplay.

3. **Dynamic Shield Display**:
    - Implemented a visible "Shield On" indicator that replaces the health display when the boss's shield is energetic.

4. **Improved Health and Score UI**:
    - Refined the layout and added steady styling for better visibility.

5. **Code Refactoring**:
   - Removed duplicate logic, progressed clarity, and ensured steady use of key objects.

---

## Project Structure

### Java Classes
- **`Controller` and `Main`**: Manages the utility lifecycle and handles transitions among levels.
- **`ActiveActor` & `ActiveActorDestructible`**: Abstract lessons defining commonplace conduct for game actors like the user aircraft and enemies.
- **`Boss`**: Implements the boss enemy, along with health, guard mechanics, and projectile assaults.
- **`BossHealthDisplay`**: Displays the boss's health or "Shield On" repute dynamically.
- **`EnemyPlane` & `EnemyProjectile`**: Represent enemy planes and their projectiles.
- **`FighterPlane`**: A base class for user and enemy planes, along with projectile firing capability.
- **`GameOverImage`**: Displays the game-over display with replay and go out alternatives.
- **`HeartDisplay`**: Manages the participant's health display.
- **`LevelParent`**: An abstract base class presenting shared functionality for all ranges.
- **`LevelOne`**: The first level implementation with fundamental gameplay mechanics.
- **`LevelTwo`**: The 2nd stage, featuring the boss combat.
- **`LevelView`**: A base elegance handling the UI for each stage.
- **`LevelViewLevelTwo`**: Extends `LevelView` to include specific mechanics for Level Two.
- **`Projectile`**: Base magnificence for all projectiles in the game.
- **`ScoreDisplay`**: Displays the player's score.
- **`ShieldImage`**: Manages the shield's visual representation for the boss.
- **`UserPlane` & `UserProjectile`**: Represent the participant character and their projectiles.
- **`WinImage`**: Displays the win display screen with replay and go out options.

### Resources
- Images:
    - Backgrounds: `background1.Jpg`, `background2.Jpg`.
    - Boss and Enemies: `bossplane.Png`, `enemyplane.Png`, `enemyFire.Png`.
    - Projectiles: `fireball.Png`.
    - Visuals: `shield.Png`, `userplane.Png`, `userfire.Png`.
    - UI: `coronary heart.Png`, `gameover.Png`, `youwin.Png`.

---

## Known Limitations
- The recreation currently includes only  levels. Future levels may be brought by way of extending `LevelParent`.
- The shield period and activation opportunity are hardcoded and may be parameterized for better manage.

---

## Future Enhancements
- Add extra levels with specific enemies and gameplay demanding situations.
- Implement a settings menu for adjusting parameters like shield duration and problem.
- Add sound effects and historical past tune for stronger consumer experience.
- Improve animations for smoother transitions and visual effects.