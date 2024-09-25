/***********************************************************************************************************************
 * @file: PARKSANDRECData.java
 * @description: This program implements a class that stores each record from the dataset
 * @author: Olivia Sturges
 * @date: September 19, 2024
 **************************************************************************************************/

public class PARKSANDRECData implements Comparable<PARKSANDRECData>{
    // season, episode_num_in_season, episode_num_overall, title, directed_by, written_by, original_air_date, us_viewers
    private Integer season;
    private Integer episode_num_in_season;
    private Integer episode_num_overall;
    private String title;
    private String directed_by;
    private String written_by;
    private String original_air_date;
    private Double us_viewers;

    // default constructor
    public PARKSANDRECData() {
        this.season = 0;
        this.episode_num_in_season = 0;
        this.episode_num_overall = 0;
        this.title = "";
        this.directed_by = "";
        this.written_by = "";
        this.original_air_date = "";
        this.us_viewers = 0.0;
    }

    // Constructor for PARKS & REC data
    public PARKSANDRECData(Integer season, Integer episode_num_in_season, Integer episode_num_overall, String title, String directed_by, String written_by, String original_air_date, Double us_viewers) {
        this.season = season;
        this.episode_num_in_season = episode_num_in_season;
        this.episode_num_overall = episode_num_overall;
        this.title = title;
        this.directed_by = directed_by;
        this.written_by = written_by;
        this.original_air_date = original_air_date;
        this.us_viewers = us_viewers;
    }

    // String interface for PARKS & REC data
    @Override
    public String toString() {
        return "Season: " + season + ", Episode Number in Season: " + episode_num_in_season + ", Overall Episode Number: " + episode_num_overall + ", Title: " + title + ", Directed By: " + directed_by + ", Written By: " + written_by + ", Original Air Date: " + original_air_date + ", US Viewers: " + us_viewers;
    }

    // Comparable interface for PARKS & REC data
    // Compares number of us viewers
    @Override
    public int compareTo(PARKSANDRECData other) {
        return this.us_viewers.compareTo(other.us_viewers);
    }

    // Equals method
    // Returns true if the object is equal to the object passed as a parameter
    public boolean equals(PARKSANDRECData other) {
        return this.season == other.season && this.episode_num_in_season == other.episode_num_in_season && this.episode_num_overall == other.episode_num_overall && this.title.equals(other.title) && this.directed_by.equals(other.directed_by) && this.written_by.equals(other.written_by) && this.original_air_date.equals(other.original_air_date) && this.us_viewers.equals(other.us_viewers);
    }

    // Getter for season
    public Integer getSeason() {
        return season;
    }

    // Setter for season
    public void setSeason(Integer season) {
        this.season = season;
    }

    // Getter for episode number in season
    public Integer getEpisodeNumInSeason() {
        return episode_num_in_season;
    }

    // Setter for episode number in season
    public void setEpisodeNumInSeason(Integer episodeNumInSeason) {
        this.episode_num_in_season = episodeNumInSeason;
    }

    // Getter for overall episode number
    public Integer getEpisodeNumOverall() {
        return episode_num_overall;
    }

    // Setter for overall episode number
    public void setEpisodeNumOverall(Integer episodeNumOverall) {
        this.episode_num_overall = episodeNumOverall;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for directed by
    public String getDirectedBy() {
        return directed_by;
    }

    // Setter for directed by
    public void setDirectedBy(String directedBy) {
        this.directed_by = directedBy;
    }

    // Getter for written by
    public String getWrittenBy() {
        return written_by;
    }

    // Setter for written by
    public void setWrittenBy(String writtenBy) {
        this.written_by = writtenBy;
    }

    // Getter for original air date
    public String getOriginalAirDate() {
        return original_air_date;
    }

    // Setter for original air date
    public void setOriginalAirDate(String originalAirDate) {
        this.original_air_date = originalAirDate;
    }

    // Getter for US viewers
    public Double getUsViewers() {
        return us_viewers;
    }

    // Setter for US viewers
    public void setUsViewers(Double usViewers) {
        this.us_viewers = usViewers;
    }


}
