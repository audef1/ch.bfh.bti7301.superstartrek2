```
 _____ _   _______ ___________   _____ _____ ___  ______   ___________ _____ _   __
/  ___| | | | ___ \  ___| ___ \ /  ___|_   _/ _ \ | ___ \ |_   _| ___ \  ___| | / /
\ `--.| | | | |_/ / |__ | |_/ / \ `--.  | |/ /_\ \| |_/ /   | | | |_/ / |__ | |/ / 
 `--. \ | | |  __/|  __||    /   `--. \ | ||  _  ||    /    | | |    /|  __||    \ 
/\__/ / |_| | |   | |___| |\ \  /\__/ / | || | | || |\ \    | | | |\ \| |___| |\  \
\____/ \___/\_|   \____/\_| \_| \____/  \_/\_| |_/\_| \_|   \_/ \_| \_\____/\_| \_/

```
#### BTI7301 - Project 1
## Projectdiary

Team:
- Auderset Florian (audef1)
- Buchegger Thomas (butt1)
- Hofer Filip (hofi1)

#### 27.09.2016 - Caffeteria Wankdorffeldstrasse
---
Erste Projektbesprechung
Anwesend:
- Brünnler Kai
- Auderset Florian
- Buchegger Thomas

Abwesend:
- Hofer Filip (kann Termin berufsbedingt nicht wahrnehmen)

Besprochen:
- Schlusspräsentation findet am 17.01.2017 in der Wankdorffeldstrasse statt. Zimmer ist noch nicht festgelegt.
- Das fertige Projekt mit allen dazugehörigen Dokumentationen, Präsentation soll bis am 10.01.2017 Kai zur Verfügung gestellt werden.
- Kai wünscht sich, dass wir ein sauber strukturiertes OO-Projekt machen. Der Spass an der Entwicklung und dem Spiel soll im Vordergrund stehen.
- Zudem sollen die Aufgaben so verteilt werden, dass jedes Teammitglied einen Teil realisiert und von den jeweils anderen Teilen nur die Interfaces kennt.

#### 28.09.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht)

Besprochen:
Wir besprechen grob die Spielmechanik und überlegen uns was das Ziel des Spiels sein kann.
Da die U.S.S. Enterprise auf Weltraumerkundung ist, soll das Ziel sein ein Level, welches in verschiedene Quadranten unterteilt ist zu durchforschen.
Das Level ist bestanden, wenn alle Quadranten besucht, und gesäubert wurden.
Je nach Quadrant hat es dort ein Meteoritenfeld, eine Raumstation, eins oder mehrere feindliche Klingonenschiffe.
Der Sektor gilt als gesäubert, wenn alle Klingonen zerstört, oder die Raumstation besucht wurde.

Der Bildschirm soll, angelehnt an die alten Spieleklassiker, in mehrere Teile geteilt werden.
- Allgemeine Schiffsinformationen (Zustand Schild, Ladezustand Phaser, Sauerstoff, Energie, etc.)
- 2D Ansicht des Weltalls inkl. Schiffe und Meteoriten (Radar).
- Nachrichtenterminal (Nachrichten von Feinden, Raumstationen oder Schiffsfunk)
- Karte des Weltraums (evtl. Toggle mit "M") 
- andere?

Beschlüsse:
Brainstorming am Freitagmorgen, 30. September 2016.

#### 05.10.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht)

Besprochen:
Im Projektmanagement müssen wir ein Requirementskatalog erstellen. Dieser soll in gesprochener Sprache verfasst werden.
Anhand der Anforderung machen wir ein Brainstorming. Als Grundlage dient uns der Spielbeschrieb auf http://www.atariarchives.org/basicgames/showpage.php?page=158.

Die Requirements sollen S.M.A.R.T. sein : Spezifisch, Messbar, Erreichbar (Achievable), Realistisch, Terminiert. Messbarkeit kann auch digital sein.

####PROJEKTZIELE:
- Ein Star Trek Spiel in klassischer 8-Bit Grafik entwickeln.
- Der Schwierigkeitsgrad soll progressiv zunehmen.
- Das Raumschiff soll Upgradebar sein. Sprich mit dem Einsammeln von Upgrades können Phaser und Schilde verbessert werden.
- Das Raumschiff soll über Tastatureingabe durch den 2 dimensionalen Raum bewegt werden.
- Die Steuerung des Schiffs soll in 8 Richtungen möglich sein  (45 Grad Abstufungen).
- Das Spiel bietet eine rudimentäre 2D Grafik.

####OUT OF SCOPE:
- Einstellungsmöglichkeiten für Grafik oder Audio
- Mehrsprachigkeit
- Level Generator
- Anpassbare Schwierigkeitsstufe (diskutierbar)

#### 14.10.2016 - Wankdorffeldstrasse 101
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht in Biel)

Besprochen:
Wir arbeiten am Domainmodel und besprechen den Aufbau des Userinterfaces.
Dafür orientieren wir uns an verschiedenen Star Trek Titeln.

Wir fertigen eine Skizze an, welche zu einem späteren Zeitpunkt als Mockup umgesetzt wird.
Das GUI besteht grob aus 5 Bereichen:
- Energieanzeige
- Waffenanzeige
- Hauptbildschirm (verschiedene Ansichten Bsp. Topdown Ansicht)
- Radar
- Nachrichtenkonsole

Filip arbeitet an der Übersetzung der "Rules and Notes" vom ersten Super Start Trek Spiel.
Dieser Input dient uns als ungefähre Vorlage der Requirements für das Spiel.

Wir beschliessen ebenfalls die Struktur des Spiels:
Das Spiel beinhaltet 64 Level (8x8 Levels). Ein Level ist widerum in 64 Quadranten unterteilt (8x8 Quadranten).
Mit der Anzahl besuchter Quadranten steigt der Schwierigkeitsgrad (Mehr Gegner, oder Gegner sind schwieriger zu eliminieren).
Sind alle Quadranten eines Levels besucht, so ist der Level gemeistert. Die Reihenfolge der Quadranten ist nicht festgelegt.
Der letzte besuchte Quadrant eines jeden Levels beinhaltet einen "Boss" Gegner.
Gleich verhält es sich mit den Levels. Je mehr Levels absolviert wurden, desto schwieriger wird das Spiel.
Das letzte besuchte Level (64) hat einen "Super End Boss" Gegner.

Wir ergänzen unser Domain Model um die Klassen Game und überlegen uns Gamestates.

#### 19.10.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip
- Buchegger Thomas

Aus den Handnotizen erstellen wir das Domain Model und das Mockup des GUI.
![Domain Model](https://github.com/audef1/ch.bfh.bti7301.superstartrek/blob/master/docs/domainmodel/SuperStarTrek.png "Domain Model")


#### 21.10.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht in Biel)

Um mit der Implementierung zu beginnen legen wir Sprintziele fest:
##### Sprint 1 (Umsetzung bis 4. November 2016)
- Window & Game Loop & Gamestates
- Input Handling
- Umsetzung der Models gemäss UML
- parametrisierbare Levels & Quadranten
- Steuerbares Raumschiff

##### Sprint 2 (Umsetzung bis 18. November 2016)
- Meteoriten
- aktive Gegner
- Kollisionen, inkl. Schaden
- Waffen, inkl. Schaden

##### Sprint 3 (Umsetzung bis 2. Dezember 2016)
- Menü (Start Game, Exit)
- Cockpit mit (Energie und Waffenanzeige, Terminal)

##### Sprint 4 (Umestzung bis 16. Dezember 2016)
- Savestates
- Level progression -> zunehmender Schwierigkeitsgrad bei den Gegnern

#### 02.11.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht)

Nachdem wir den Projektplan Herrn Gasenzer zeigten, kamen noch einige Verbesserungsvorschläge hinzu.
Im späteren Verlauf des Abends passten wir die Ziele und Funktionalen/Nichtfunktionalen Bedingungen an und haben diese 
Aufgeteilt.
Zudem haben wir noch einige Bedingungen hinzugefügt, um den Projektablauf besser definieren zu können.

Wir haben die Domain Models, welche zu programmieren sind, jeweils einem Programmierer aufgeteilt.

#### 09.11.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Hofer Filip

Abwesend:
- Buchegger Thomas (hat Unterricht in Biel)

Models gemäss Diagramm erstellt

#### 09.11.2016 - Caffeteria Wankdorffeldstrasse
---
Anwesend:
- Auderset Florian
- Buchegger Thomas

Abwesend:
- Hofer Filip

Neue Ideen bezüglich dem Erstellen der Levels und Quadranten besprochen.
