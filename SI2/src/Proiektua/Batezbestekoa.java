package Proiektua;

public class Batezbestekoa implements Normalizazioa {

	public Batezbestekoa(){}

	public Bektorea[] matrizeaNormalizatu(Bektorea[] mat) {
		Bektorea[] matNormalizatua = new Bektorea[mat.length];
		for(int i=0; i<mat.length; i++) {
			matNormalizatua[i] = new Bektorea();
			double batezbestekoa = mat[i].bektorearenBatezbestekoa();
			for(int j=0; j<mat[i].luzera(); j++) {
				matNormalizatua[i].gehituElementua(mat[i].getElementuarenIdErreala(j), mat[i].getPosiziokoBalioa(j)-batezbestekoa); // dagokion balorazioari batezbestekoa kentzen zaio
			}
		}
		return matNormalizatua;
	}
	
}
