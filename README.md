# bup-to-midi-converter
This Java Swing application converts Yamaha backup files to midi files.
## Description
If you have issues transferring your Yamaha backup files _(.bup)_ from your computer to your instrument with freeware like [__YAMAHA Musicsoft Downloader__](https://europe.yamaha.com/en/support/updates/msd_win_kbd.html),
to relisten your music, then this converter might help. It cannot transfer your backups to your instrument, but it can convert backup files
to midi files on your computer.
## How-To
1. When the application has started, choose a .bup-file you want to convert by entering its path to the input textfield. If you choose the
   backup file via file dialog, a default output path will be created. The converted midi files will be saved in the input directory and will
   be named like the input file. In that case, you can skip step 2.

   Example:
   ```
   C:\yamaha backup files\08PK61.BUP
   ```
2. Enter the output path in the output textfield, where all midi files from your backup file will be saved.

   Example:
   ```
   C:\yamaha backup files\converted 08PK61\midi
   ```
   _The last part of the output path describes the name of the midi files, that will be converted. All converted midi files will be saved
   in_ `converted 08PK61` _and named like_ `midi_[index].mid` _._
3. Press the `Convert` button. If the conversion has finished, the output directory will open, where you should see all converted midi files.
## Personal Experience
I've tested this converter with my _YAMAHA PSR E413_ backup files. Fortunately, I can transfer backups from my instrument to my computer
with the __YAMAHA Musicsoft Downloader__, but not the other way around. To test the capability of the converter, I recorded all 5 user files
each with 5 tracks on my instrument. Then, I used the Musicsoft Downloader to transfer a new backup with my recorded user files to my
computer. With the converter, I could revive 21 out of 25 possible midi files out of this backup. This and other problems are described
in detail in the issues tab.
## Source
I have used the algorithm shown on this site to program the converter:
<https://habr.com/en/post/81831/>
