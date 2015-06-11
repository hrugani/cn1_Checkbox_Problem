/**
 * Your application code goes here
 */

package userclasses;

import generated.StateMachineBase;
import com.codename1.ui.*; 
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Your name here
 */
public class StateMachine extends StateMachineBase {
    public StateMachine(String resFile) {
        super(resFile);
        // do not modify, write code in initVars and initialize class members there,
        // the constructor might be invoked too late due to race conditions that might occur
    }
    
    /**
     * this method should be used to initialize variables instead of
     * the constructor/class scope to avoid race conditions
     */
    protected void initVars(Resources res) {
    }

    @Override
    protected void postMain(Form f) {
        MyForm1 myForm1 = new MyForm1();
        myForm1.show();
    }
    
    
    class MyForm1 extends Form {
        
        public MyForm1() {
            super();
            setTitle("FORM 1");
            setScrollableY(false);
            MultiList ml = createMultuList();
            Button btn = createButton();
            Container c = getContentPane();
            c.setLayout(new BorderLayout());
            c.addComponent(BorderLayout.CENTER, ml);
            c.addComponent(BorderLayout.SOUTH, btn);            
        }

        private MultiList createMultuList() {
            MultiList multilist = new MultiList();
            multilist.setHandlesInput(true);
            multilist.setFireOnClick(true);
            multilist.getSelectedButton().setCheckBox(true);
            multilist.getUnselectedButton().setCheckBox(true);
            multilist.setLongPointerPressActionEnabled(false);
            multilist.setModel(createListModel());
            return multilist;
        }
        
        private ListModel createListModel() {
            
            ArrayList itemList = new ArrayList();
            HashMap<String,Object> itemMap = null;

            for (int i = 0; i < 5;i++) {
                itemMap = new HashMap<String,Object>();
                itemMap.put("Line1", "Checkbox " + i);
                itemMap.put("emblem", "false");
                itemList.add(itemMap);
            }

            DefaultListModel lm = new DefaultListModel(itemList);

            return lm;
        }        

        private Button createButton() {
            Button button = new Button("Button");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    final Form myForm2 = new MyForm2();
                    Command backCommand = new Command("Back") {
                        @Override
                        public void actionPerformed(ActionEvent ev) {
                            MyForm1.this.showBack();
                        } 
                    }; 
                    myForm2.setBackCommand(backCommand);
                    myForm2.show();                            
                }
            });
            return button;
        }
        
    }
    
    
    class MyForm2 extends Form { 
        public MyForm2() {
            setTitle("FORM 2");
        }
    }

}
