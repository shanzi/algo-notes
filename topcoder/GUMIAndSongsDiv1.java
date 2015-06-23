/*
 * Let's say we have picked some songs to sing, the smallest time cost will be that we sort
 * the songs by their tone value. In this case, as the song set is decided, so the total
 * duration time do not depend on the order of song, and the minimal time cost on switching
 * songs is max(tones) - min(tones).
 *
 * So at first we can fix the maximal tone and minimal tone and consider only the songs
 * in this range. Let S denotes the set contains these songs. No matter how we pick a sub set of S,
 * we can always arrange the songs that the time cost of switching tones is minimal. And this
 * minimal is must lower or equal to that for S. As the order of duration does not matter,
 * as to pick as many songs as possible from S, we pick from the song with shortest duration
 * to longest until the sum of duration will exceed T minus the max tone difference in S.
 *
 * We iterate through all subset according to the order of tones, then we will get the maxinum
 * number of songs GUMI can sing.
 */

import java.util.*;

class Song {
    int duration;
    int tone;

    Song(int duration, int tone) {
        this.duration = duration;
        this.tone = tone;
    }
}

public class GUMIAndSongsDiv1 {
    
    public static int maxSongs(int[] duration, int[] tone, int T) {
        int maxCount = 0;
        Song[] allSongs = new Song[duration.length];
        for (int i = 0; i < duration.length; i++) {
            allSongs[i] = new Song(duration[i], tone[i]);
        }
        Arrays.sort(allSongs, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.tone - b.tone;
            }
        });

        for (int l = 0; l < allSongs.length; l++) {
            for (int r = l; r < allSongs.length; r++) {
                Song[] subSongs = new Song[r - l + 1];
                for (int j = 0; j < subSongs.length; j++) {
                    subSongs[j] = allSongs[l + j];
                }

                Arrays.sort(subSongs, new Comparator<Song>() {
                    public int compare(Song a, Song b) {
                        return a.duration - b.duration;
                    }
                });

                int current = T + allSongs[l].tone - allSongs[r].tone;
                int count = 0;
                for (Song s: subSongs) {
                    if (current - s.duration >= 0) {
                        current -= s.duration;
                        count++;
                    } else {
                        break;
                    }
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

}
