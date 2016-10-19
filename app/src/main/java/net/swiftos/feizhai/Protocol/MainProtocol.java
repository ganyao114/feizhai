package net.swiftos.feizhai.Protocol;

/**
 * Created by gy939 on 2016/10/18.
 */

public interface MainProtocol {

    public final static String MainContext = "main_context";

    public interface View{
        public void setText(String txt);
    }

    public interface Presenter{

    }
}
