package test;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JFrameFunction {
    public static String a = "";

    public static void drawFrame() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        final JCheckBox getAllSubFilesCheckBox = new JCheckBox("getAllSubFiles");
        final JTextArea NameSpaceText = new JTextArea();
        final JLabel NameSpaceReflectLabel = new JLabel();
        Label InformationLabel = new Label();
        InformationLabel.setText("Default output location: D:\\Document\\Java\\classConversionOutput\\");
        JButton ExploreButton = new JButton("BrowseToConvertJ2DoNet");
        JButton GenerateUrlButton = new JButton("GenerateUrl");
        JButton AddUserExploreButton = new JButton("ADDUser");
        JButton IAddUserExploreButton = new JButton("IAddUser");
        JButton setOutputLocationButton = new JButton("Set Output Location");
        JLabel label = new JLabel("NameSpace:");
        setOutputLocationButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                FileConvertUtil.setOutputLocation();
            }

        });
        ExploreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String xString = NameSpaceText.getText();
                try {
                    NameSpaceReflectLabel.setText(NameSpaceText.getText());
                    FileConvertUtil.convertJ2DoNetClass(".java", getAllSubFilesCheckBox.isSelected(), xString);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        AddUserExploreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    FileConvertUtil.addUser(null, getAllSubFilesCheckBox.isSelected(), null);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        IAddUserExploreButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    FileConvertUtil.IaddUser(null, getAllSubFilesCheckBox.isSelected(), null);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GenerateUrlButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    GenerateURlAndMethod.test();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        // NameSpaceText.setText("input here");
        NameSpaceReflectLabel.setText(NameSpaceText.getText());
        // 当TextArea里的内容过长时生成滚动条
        JScrollPane nameSpaceTextScrollPane = new JScrollPane(NameSpaceText);
        JScrollPane NameSpaceReflectScrollPaneLabel = new JScrollPane(NameSpaceReflectLabel);
        JScrollPane NameSpaceLabelScrollPane = new JScrollPane(label);
        panel.add(nameSpaceTextScrollPane);
        panel.add(NameSpaceReflectScrollPaneLabel);
        panel.add(ExploreButton);
        panel.add(GenerateUrlButton);
        panel.add(AddUserExploreButton);
        panel.add(IAddUserExploreButton);
        panel.add(NameSpaceLabelScrollPane);
        panel.add(getAllSubFilesCheckBox);
        panel.add(InformationLabel);
        panel.add(setOutputLocationButton);
        /**
         * 设置布局
         */
        panel.setLayout(null);
        /**
         * 添加组件
         */
        InformationLabel.setBounds(160, 150, 500, 30);
        NameSpaceReflectScrollPaneLabel.setBounds(400, 10, 100, 70);
        NameSpaceLabelScrollPane.setBounds(10, 10, 100, 30);
        getAllSubFilesCheckBox.setBounds(160, 100, 200, 30);
        ExploreButton.setBounds(400, 200, 200, 30);
        setOutputLocationButton.setBounds(160, 200, 200, 30);
        AddUserExploreButton.setBounds(640, 200, 200, 30);
        IAddUserExploreButton.setBounds(640, 400, 200, 30);
        GenerateUrlButton.setBounds(400, 400, 200, 30);
        nameSpaceTextScrollPane.setBounds(160, 10, 200, 30);
        frame.add(panel);
        /**
         * 设置尺寸
         */
        frame.setSize(1500, 600);
        frame.setVisible(true);
    }
}
