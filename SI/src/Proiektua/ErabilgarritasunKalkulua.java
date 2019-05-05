package Proiektua;


public class ErabilgarritasunKalkulua {
	
	private static ErabilgarritasunKalkulua nErabilgarritasunKalkulua;
	private ErabilgarritasunMetodoa metodoa;
	
	private ErabilgarritasunKalkulua() {
		metodoa = new TfIdf();
	}
	
	public static synchronized ErabilgarritasunKalkulua getErabilgarritasunKalkulua() {
		if(nErabilgarritasunKalkulua == null) {
			nErabilgarritasunKalkulua = new ErabilgarritasunKalkulua();
		}
		return nErabilgarritasunKalkulua;
	}
	
	public Bektorea[] erabilgarritasunMatrizeaBete(Bektorea[] mat) {
		return metodoa.erabilgarritasunMatrizeaBete(mat);
	}
	
	public void metodoaAldatu(ErabilgarritasunMetodoa pMetodoa) {
		metodoa = pMetodoa;
	}
}
