import java.awt.*;
import javax.swing.*;




public class PrincipalComp extends JFrame{

        //Visuals of frame base
PrincipalComp() {
    JFrame frame = new JFrame("Sumo Trafic Simulation");
    frame.setSize(1000, 800);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());


    JPanel titel_panel = new JPanel();
    JPanel buttons_panel = new JPanel();
    JPanel options_panel = new JPanel();
    JPanel slider_panel = new JPanel();
    JPanel moreOpt_panel = new JPanel();
    JPanel labl_panel = new JPanel();
    JLabel textfield = new JLabel("select");
    JLabel speed_label = new JLabel("Speed");
    JLabel color_label = new JLabel("Color");
    JSlider slider = new JSlider(0, 100, 60);
    slider.setMinorTickSpacing(0);
    slider.setMajorTickSpacing(100);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setLabelTable(slider.createStandardLabels(20));
    //to be edited!!!
    //JComboBox<String> combo = new JComboBox<>(new String[]{"Rojo", "Verde", "Azul"});


    textfield.setBackground(new Color(50, 60, 100));
    textfield.setForeground(new Color(200, 255, 0));
    textfield.setFont(new Font("Ink Free", Font.ITALIC, 60));
    textfield.setHorizontalAlignment(JLabel.CENTER);
    textfield.setText("SumoSimulation");
    textfield.setOpaque(true);

    titel_panel.setLayout(new BorderLayout());
    titel_panel.setPreferredSize(new Dimension(100, 300));


    //buttons for options_panel
    buttons_panel.setLayout(new GridLayout(2, 2));

    buttons_panel.add(new JButton("Start"));
    buttons_panel.add(new JButton("Stop"));
    buttons_panel.add(new JButton("+Add Car"));
    //buttons_panel.add(new JButton("Edit Traffic\nLights"));

    options_panel.setLayout(new BorderLayout());

    //slider for setting speed, appears in options_panel
    slider_panel.setLayout(new BorderLayout());


    //Panel Instance for Cars
    SimulAutosP simulation_Panel = new SimulAutosP();
    frame.add(simulation_Panel, BorderLayout.CENTER);

    //auto creation
    Cars car1 = new Cars(0, 150);
    Cars car2 = new Cars(30, 100);
    simulation_Panel.addCar(car1);
    simulation_Panel.addCar(car2);


    //Labels for labl_panel show information about number of cars, avg speed, etc
    labl_panel.setLayout(new GridLayout(1, 2, 10, 10));

    JLabel info_label1 = new JLabel("Number of cars: " + Cars.getCarsCount(), SwingConstants.CENTER);
    info_label1.setOpaque(true);
    info_label1.setBackground(Color.YELLOW);
    info_label1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    labl_panel.add(info_label1);

    JLabel info_label2 = new JLabel("Average Speed: ", SwingConstants.CENTER);
    info_label2.setOpaque(true);
    info_label2.setBackground(Color.YELLOW);
    info_label2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    labl_panel.add(info_label2);


    options_panel.add(moreOpt_panel, BorderLayout.EAST);
    options_panel.add(buttons_panel, BorderLayout.NORTH);
    options_panel.add(slider_panel, BorderLayout.SOUTH);
    slider_panel.add(speed_label, BorderLayout.NORTH);
    slider_panel.add(slider, BorderLayout.SOUTH);
    frame.add(options_panel, BorderLayout.EAST);
    titel_panel.add(textfield);

    frame.add(labl_panel, BorderLayout.SOUTH);
    frame.add(titel_panel, BorderLayout.NORTH);
    frame.setVisible(true);
}

}


