package Proiektua;

public class NormalizazioKalkulua {

	private static NormalizazioKalkulua nNormalizazioKalkulua;
	private Normalizazioa normalizazioMota;
	
	private NormalizazioKalkulua() {
		normalizazioMota = new Batezbestekoa();	// Z-Score normalizazio era erabiltzen dugu
	}
	
	public static synchronized NormalizazioKalkulua getNormalizazioKalkulua() {
		if(nNormalizazioKalkulua == null) {
			nNormalizazioKalkulua = new NormalizazioKalkulua();
		}
		return nNormalizazioKalkulua;
	}
	
	public Bektorea[] matrizeaNormalizatu(Bektorea[] mat){
		return normalizazioMota.matrizeaNormalizatu(mat);
	}
	
	public void normalizazioMotaAldatu(Normalizazioa pMota) {
		normalizazioMota = pMota;
	}
	
}
