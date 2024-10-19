package com.springframework.sp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.springframework.sp.node.NodeData;



@Controller
public class SpController {

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String getNodeWeight(@ModelAttribute NodeData NodeData, Model model) {
        NodeData.runApp();
        model.addAttribute("nodeResult", NodeData.getResult());
        return "result";
    }
}
