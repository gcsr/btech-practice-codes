


public interface IBoard {
	public void createBoard();
	public void recreateBoard();
	public void putPieces();
	public void setGameProgress(boolean running);
	public boolean getGameProgress();
	public void setBoardPiece(int x, int y, int val);
	public int getBoardPiece(int x, int y);
	public int getPlayer();
	public void setPlayer(int iplayer);
	public void reputPieces();
	public void setTotal_blue_pieces(int total_blue_pieces);
	public int getTotal_blue_pieces();
	public void setTotal_orange_pieces(int total_orange_pieces);
	public int getTotal_orange_pieces();
    public void setFromPosition(int x, int y);
    public void setToPosition(int x, int y);
    public void setPlayerPosChange(boolean iPlayerPosChange);
    public boolean getPlayerPosChange();

}
