package Proiektua;

public class Zscore implements Normalizazioa{
	
	public Zscore() {}

	public Bektorea[] matrizeaNormalizatu(Bektorea[] mat) {
		Bektorea[] matNormalizatua = new Bektorea[mat.length];
		for(int i=0; i<mat.length; i++) {
			matNormalizatua[i] = new Bektorea();
			double batezbestekoa = mat[i].bektorearenBatezbestekoa();
			double desbiderapenEstandarra = mat[i].bektorearenDesbiderapenEstandarra();
				for(int j=0; j<mat[i].luzera(); j++) {
					if(desbiderapenEstandarra == 0) {
						matNormalizatua[i].gehituElementua(mat[i].getElementuarenIdErreala(j), 0);
					}
					else {
						matNormalizatua[i].gehituElementua(mat[i].getElementuarenIdErreala(j), (double)(mat[i].getPosiziokoBalioa(j)-batezbestekoa)/desbiderapenEstandarra);		// dagokion balorazioari batezbestekoa kentzen zaio eta desbiderapen estandarra zatitzen zaio
					}
				}
		}
		return matNormalizatua;
	}
	
}
