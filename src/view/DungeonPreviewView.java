package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class DungeonPreviewView extends JFrame implements IPreview {

  private JLabel display;
  private final JTextField rows;
  private final JTextField cols;
  JRadioButton wrapping;
  JRadioButton nonWrapping;
  private final JTextField interconnectivity;
  private final JTextField treasurePercentage;
  private final JTextField monster;
  private final JTextField name;
  private final JButton buildDungeon;
  private final JPanel fieldsPanel;

  public DungeonPreviewView(String caption) {
    super(caption);
    fieldsPanel = new JPanel();
    fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
    setSize(600, 600);
    setLocation(200, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new FlowLayout());

    display = new JLabel("Enter Dungeon details ");
    fieldsPanel.add(display);

    //rows text field
    display = new JLabel(("Rows: "));
    fieldsPanel.add(display);
    rows = new JTextField(10);
    fieldsPanel.add(rows);

    //cols text field
    display = new JLabel(("Columns: "));
    fieldsPanel.add(display);
    cols = new JTextField(10);
    fieldsPanel.add(cols);

    //wrap text field
    display = new JLabel(("Wrapping: "));
    fieldsPanel.add(display);
    wrapping = new JRadioButton();
    nonWrapping = new JRadioButton();
    wrapping.setText("Wrapping");
    nonWrapping.setText("Non-Wrapping");
    fieldsPanel.add(wrapping);
    fieldsPanel.add(nonWrapping);

    //interconnectivity text field
    display = new JLabel(("Interconnectivity: "));
    fieldsPanel.add(display);
    interconnectivity = new JTextField(10);
    fieldsPanel.add(interconnectivity);

    //treasure text field
    display = new JLabel(("Treasure Percentage: "));
    fieldsPanel.add(display);
    treasurePercentage = new JTextField(10);
    fieldsPanel.add(treasurePercentage);

    //monster text field
    display = new JLabel(("Monsters: "));
    fieldsPanel.add(display);
    monster = new JTextField(10);
    fieldsPanel.add(monster);

    //name text field
    display = new JLabel(("Player Name: "));
    fieldsPanel.add(display);
    name = new JTextField(10);
    fieldsPanel.add(name);

    //echo button
    buildDungeon = new JButton("Build Dungeon");
    buildDungeon.setActionCommand("Build Dungeon");
    fieldsPanel.add(buildDungeon);

    this.add(fieldsPanel, BorderLayout.PAGE_START);
    setVisible(true);
  }


  @Override
  public List<String> getDungeonParameters() {
    List<String> parameters = new ArrayList<>();
    parameters.add(rows.getText());
    parameters.add(cols.getText());
    if(wrapping.isSelected()){
      parameters.add("true");
    } else if (nonWrapping.isSelected()) {
      parameters.add("false");
    }
    parameters.add(interconnectivity.getText());
    parameters.add(treasurePercentage.getText());
    parameters.add(monster.getText());
    parameters.add(name.getText());
    this.dispose();
    return parameters;

  }

  @Override
  public void addActionListener(ActionListener listener) {
    buildDungeon.addActionListener(listener);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
