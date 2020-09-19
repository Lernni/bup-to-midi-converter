# bup-to-midi-converter
>This Java Swing application converts Yamaha backup files _(.bup)_ to midi files.

_Note that not all Yamaha instruments store their backup files in the same way. It is possible, that user songs are stored seperately, apart from the backup file. Before trying out this converter, consider checking your instrument's user manual to find out, whether your user songs are actually encoded in the backup file or not.
<br><br>I'm not sure, how many Yamaha instruments actually store their user songs in the backup file. If you found out, that your instrument stores them seperately, consider commenting your instrument's name, so I can add it to the list of [not supported devices](#not-supported)._

## Instructions
1. When the application has started, choose a .bup-file you want to convert by entering its path to the input textfield. If you choose the
   backup file via file dialog, a default output path will be created. The converted midi files will be saved in the input directory and will
   be named like the input file. In that case, you can skip step 2.

   Example:
   ```
   C:\yamaha_backup_files\08PK61.BUP
   ```
2. Enter the output path in the output textfield, where all midi files from your backup file will be saved.

   Example:
   ```
   C:\yamaha_backup_files\converted_08PK61\midi
   ```
   _The last part of the output path describes the name pattern of the midi files, that will be converted. All converted midi files will be saved
   in_ `converted_08PK61` _and will be named like_ `midi_[index].mid` _._
3. Press the `Convert` button. If the conversion has finished, the output directory will open, where you should see all converted midi files.
## Personal Experience
I've tested this converter with my _YAMAHA PSR E413_ backup files. Although I can transfer backups from my instrument to my computer
with the [YAMAHA Musicsoft Downloader](https://usa.yamaha.com/support/updates/msd_win_kbd.html), it's not working the other way around. So, I could not listen to my user songs and I needed a way to extract them from the backup files. To test the capability of the converter, I recorded all 5 user songs
each with 5 tracks on my instrument. Then, I used the Musicsoft Downloader to transfer a new backup with my recorded user songs to my
computer. With the converter, I could revive 21 out of 25 possible midi files out of this backup.
## Not Supported
- YAMAHA YDP-161/YDP-141
## Known Issues
- [empty or not working midi files are converted](/../../issues/3)
- [Not all user tracks are converted to midi files](/../../issues/2)
## Source
I have used the algorithm shown on this site to create the converter:
<https://habr.com/en/post/81831/>
