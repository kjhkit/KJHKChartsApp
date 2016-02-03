public class Album 
{
	int m_plays;
	String m_albumName;
	String m_artistName;
	
	//Returns: a string representation of the album object
	public String toString()
	{
		return m_albumName + " by " + m_artistName + " has " + m_plays + " plays";
	}
	
	
	//Album constructor from individual parameters
	public Album(String albumName, String artistName)
	{
		m_plays = 1;
		m_albumName = albumName;
		m_artistName = artistName;
	}
	
	//Album constructor from Entry
	public Album(Entry entry)
	{
		m_plays = 1;
		m_albumName = entry.getAlbum();
		m_artistName = entry.getArtist();
	}
	
	//Returns a boolean representing the equality of this Album and the input Album
	boolean equals(Album album)
	{
		return(this.m_albumName.equals(album.getAlbum()) &&
			   this.m_artistName.equals(album.getArtist()));
	}
	
	//Returns a boolean representing the equality of this Album and an input Entry
	boolean equals(Entry entry)
	{
		return(this.m_albumName.equals(entry.getAlbum()) &&
				   this.m_artistName.equals(entry.getArtist()));
	}
	
	String getArtist()
	{
		return m_artistName;
	}
	
	String getAlbum()
	{
		return m_albumName;
	}
	
	int getPlays()
	{
		return m_plays;
	}
	
	void addPlay()
	{
		m_plays++;
	}
}
