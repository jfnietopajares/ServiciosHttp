package Hilos;

/**
 *
 * @author 06551256M
 */
public class HilosInformes extends Thread {

    @Override
    public void run() {
        HiloPaciente hiloPaciente = new HiloPaciente("29765");
        hiloPaciente.start();
    }

}
