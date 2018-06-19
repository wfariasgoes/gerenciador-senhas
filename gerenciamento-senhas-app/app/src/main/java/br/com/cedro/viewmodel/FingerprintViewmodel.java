package br.com.cedro.viewmodel;

public class FingerprintViewmodel {

    private FingerprintListner listner;

    public FingerprintViewmodel(FingerprintListner listner) {
        this.listner = listner;
    }

    public void onClickCheck(){
        listner.onClickCheck();
    }

    public void onClickC(){
        listner.onClickCheck();
    }

    public interface  FingerprintListner{
        void onClickCheck();
        void onClickSendContinue();
    }
}
