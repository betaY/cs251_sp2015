public class PropertyTesters {
	
	public static boolean testForHeapness (Song[] songs) {
        int startIndex = getParentIndex(songs.length-1);
        for (int i = startIndex; i >= 0; i--) {
            int check = checkHeapify(songs, songs.length, i);
            if (check == 0) {
                return false;
            }
        }
        return true;
	}
    
    public static int checkHeapify(Song[] songs, int heapSize, int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int out = 0;
        
        int largest = index;
        if (left < heapSize && songs[largest].getNumberOfTimesPlayedSoFar() < songs[left].getNumberOfTimesPlayedSoFar()) {
			return 0;
		}
		if (right < heapSize && songs[largest].getNumberOfTimesPlayedSoFar() < songs[right].getNumberOfTimesPlayedSoFar()) {
			return 0;
		}
		return 1;
    }
    public static int getParentIndex(int current) {
        return (current - 1)/2;
    }
    public static int getLeftChildIndex(int current) {
        return current*2 + 1;
    }
    public static int getRightChildIndex(int current) {
        return current*2 + 2;
    }
    	
	public static boolean testDataConsistency (SongsRecord record) {
		int numberOfSongs = record.getNumberOfSongs();
		Song[] songsHeap = record.getHeapOfSongs();
		int[] songPositions = record.getPositionsOfSongs();
		int[] songsCounts = record.getSongPlayedCounts();
		
		boolean isConsistent = true;

		for(int i = 0; i < numberOfSongs && isConsistent; ++i) {
			isConsistent = isConsistent && (songsHeap[songPositions[i]].getNumberOfTimesPlayedSoFar() == songsCounts[i]); 
			// System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songsCounts = "+isConsistent+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
			isConsistent = isConsistent && (songPositions[songsHeap[i].getSongID()] == i);
			// System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songPositions = "+isConsistent+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
		}

		return isConsistent;
	}
}


