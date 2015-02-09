
public class SongsRecord {
	private int nSongs;
	
	private Song[] songsHeap;
	private int[] songsCount;
	private int[] songsPosition;

	public SongsRecord(int nSongs) {
		this.nSongs = nSongs;
		
		this.songsHeap = new Song[nSongs];
		this.songsCount = new int[nSongs];
		this.songsPosition = new int[nSongs];
	}
		
	public void addSongEntry(int songID, int numberOfTimesPlayed) {
		this.songsHeap[songID] = new Song(songID, numberOfTimesPlayed);
	}
	
	public Song[] getHeapOfSongs() {
		return this.songsHeap;
	}
	
	public int[] getPositionsOfSongs() {
		return this.songsPosition;
	}
	
	public int[] getSongPlayedCounts() {
		return this.songsCount;
	}
	
	public int getNumberOfSongs() {
		return this.nSongs;
	}

	public void process() {
		/* TODO Write code to:
			(1) Turn 'songsHeap' into a valid heap
			(2) Populate 'songsCount' and 'songsPosition' correctly */
		buildMaxHeap(songsHeap);
		for (int i = 0; i < songsHeap.length; i++) {
			songsCount[i] = songsHeap[i].getNumberOfTimesPlayedSoFar();
		}
	}

	// build maxHeap
	public void buildMaxHeap(Song[] songsHeap) {
		int startIndex = getParentIndex(songsHeap.length - 1);
		for (int i = startIndex; i >= 0; i++) {
			maxHeapify(songsHeap, songsHeap.length, i);
		}
	}

	public static void maxHeapify(Song[] songsHeap, int heapsize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);

		int largest = index;

		if (left < heapsize && Song[index].getNumberOfTimesPlayedSoFar() < Song[left].getNumberOfTimesPlayedSoFar()) {
			largest = left;
		}
		if (right < heapsize && Song[largest].getNumberOfTimesPlayedSoFar() < Song[right].getNumberOfTimesPlayedSoFar()) {
			largest = right;
		}

		if (largest != index) {
			int tmp = Song[index];
			Song[index] = Song[largest];
			Song[largest] = tmp;
			maxHeapify(Song, heapsize, largest);
		}
	}

	public static int getParentIndex(int current) {
		return (current - 1) / 2;
	}
	public static int getLeftChildIndex(int current) {
		return current*2 + 1;
	}
	public static int getRightChildIndex(int current) {
		return current*2 + 2;
	}


	
	public void addSongOccurrence(int songID) {
		/* TODO Write code to:
			(1) Read 'songsPosition' and get the position of the song in the heap. Then increase the count by 1. After increasing the count by 1, move the song up if required and ensure that the heap invariant is maintained.
			(2) Modify 'songsPosition' and 'songsCount' accordingly to ensure that everything is consistent.
		*/
	}

	public String getTopTwo() {
		String output = "";
		
		// Get top song
		output = Integer.toString(songsHeap[0].getSongID());
		output += " (" + Integer.toString(songsHeap[0].getNumberOfTimesPlayedSoFar())  + ")";
		
		// Get runner up
		int runnerUp = 2;
		if(songsHeap[1].getNumberOfTimesPlayedSoFar() > songsHeap[2].getNumberOfTimesPlayedSoFar() ||
				(songsHeap[1].getNumberOfTimesPlayedSoFar() == songsHeap[2].getNumberOfTimesPlayedSoFar() 
				&& songsHeap[1].getSongID() < songsHeap[2].getSongID()))
			runnerUp = 1;
		output += "; " + Integer.toString(songsHeap[runnerUp].getSongID());
		output += " (" + Integer.toString(songsHeap[runnerUp].getNumberOfTimesPlayedSoFar())  + ")";

		return output;
	}
}
