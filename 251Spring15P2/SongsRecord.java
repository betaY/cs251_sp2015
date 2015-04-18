
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


		buildMaxHeap(songsHeap);
		for (int i = 0; i < songsHeap.length; i++) {
            songsPosition[songsHeap[i].getSongID()] = i;
		}
		for (int i = 0; i < songsHeap.length; i++) {
			songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
		}
	}

	// build maxHeap
	public void buildMaxHeap(Song[] songsHeap) {
		int startIndex = getParentIndex(songsHeap.length - 1);
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(songsHeap, songsHeap.length, i);
		}
		for (int i = startIndex; i >= 0; i--) {
			checkId(songsHeap, songsHeap.length, i);
		}
	}

	// check id order
	public void checkId(Song[] songsHeap, int heapsize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		int largest = index;
		if (left < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[left].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getSongID() > songsHeap[left].getSongID()) {
				largest = left;
			}
		}
		if (right < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[right].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getSongID() > songsHeap[right].getSongID()) {
				largest = right;
			}
		}
		if (largest != index) {
			Song tmp = songsHeap[index];
			songsHeap[index] = songsHeap[largest];
			songsHeap[largest] = tmp;
			checkId(songsHeap, heapsize, largest);
		}
	}

	public static void maxHeapify(Song[] songsHeap, int heapsize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);

		int largest = index;

		if (left < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() <= songsHeap[left].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[left].getNumberOfTimesPlayedSoFar()) {
				if (songsHeap[largest].getSongID() > songsHeap[left].getSongID())
					largest = left;
				else {
					largest = largest;
				}
			} else {
				largest = left;
			}
		}
		if (right < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() <= songsHeap[right].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[right].getNumberOfTimesPlayedSoFar()) {
				if (songsHeap[largest].getSongID() > songsHeap[right].getSongID())
					largest = right;
				else {
					largest = largest;
				}
			} else {
				largest = right;
			}
		}

		if (largest != index) {
			Song tmp = songsHeap[index];
			songsHeap[index] = songsHeap[largest];
			songsHeap[largest] = tmp;
			maxHeapify(songsHeap, heapsize, largest);
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
        songsHeap[songsPosition[songID]].increasePlayedCountByOne();
        buildMaxHeap(songsHeap);
        for (int i = 0; i < songsHeap.length; i++) {
            	songsPosition[songsHeap[i].getSongID()] = i;
		}
		for (int i = 0; i < songsHeap.length; i++) {
			songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
		}

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
