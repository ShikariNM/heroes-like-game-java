## Introduction
The game is a self-playing game, that was inspired by Heroes of Might and Magic series and imitates the combat mode of it in a simple way. The idea of the project was suggested by the teacher, Eugene Finogenov, within the course Object-Oriented Programming for practice and implemented by me.

## Description
The game has two versions: console and desktop. The last one was made in android-studio IDE. The game looks quite similar to a combat mode of the inspiring game and uses analogous approach. There is a field 10 x 10 and two teams on the opposite sides of the field. Each team consists of 10 characters. Each character belongs to one of 7 classes: 3 are unique for each team (Peasant, Archer, Enchanter for one side and Spearman, Crossbowman, Monk for another) and 1 can be a part of both (Bandit). Each class has unique characteristics and abilities. Teams are formed randomly.

## Gameplay
The game process is quite primitive. It is divided by cycles. Each cycle includes one step of all alive characters. During a step, characters can move, hit, use abilities depending on a character class and a situation on the field. After each cycle, the game pauses and waits for a signal to resume. The signal is «Enter» button press in console version and «Left mouse button» or touchpad press in desktop version. The game continues unless there are no alive characters left in one team.

## Screenshots
![Desktop version screenshot](readme_images/desktop_version_1.png)
![Desktop version screenshot](readme_images/desktop_version_2.png)
![Desktop version screenshot](readme_images/console_version_1.png)
![Desktop version screenshot](readme_images/console_version_2.png)
![Desktop version screenshot](readme_images/console_version_3.png)