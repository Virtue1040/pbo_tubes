package module;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Theme {
    public static void initTheme(Window frame) {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        SwingUtilities.updateComponentTreeUI(frame);
        /* Flatlaf Customization Theme */
        UIManager.put("ScrollPane.border", Color.RED);
        System.setProperty( "flatlaf.menuBarEmbedded", "true");
        UIManager.put("TitlePane.menuBarEmbedded", true);
        UIManager.put("TextComponent.arc", 15);

        UIManager.put("Button.default.background", Color.decode("#d2a04b"));
        UIManager.put("Button.default.focusColor", Color.decode("#b0853d"));
        UIManager.put("ComboBox.buttonBackground", Color.decode("#d2a04b"));
        UIManager.put("MenuItem.selectionBackground", Color.decode("#d2a04b"));
        UIManager.put("MenuBar.selectionBackground", Color.decode("#d2a04b"));
        UIManager.put("Table.selectionBackground", Color.decode("#b0853d"));
        UIManager.put("Table.cellFocusColor", Color.decode("#d2a04b"));
        UIManager.put("Tree.selectionBackground", Color.decode("#b0853d"));
        UIManager.put("List.selectionBackground", Color.decode("#b0853d"));
        UIManager.put("ComboBox.selectionBackground", Color.decode("#d2a04b"));
        UIManager.put("Menu.selectionBackground", Color.decode("#d2a04b"));
        UIManager.put("Component.focusColor", Color.decode("#b0853d"));
        UIManager.put("Component.focusedBorderColor", Color.decode("#b0853d"));
        UIManager.put("CheckBox.icon[filled].selectedBackground", Color.decode("#d2a04b"));
        UIManager.put("TabbedPane.underlineColor", Color.decode("#d2a04b"));
        /* ========================== */
    }
}
