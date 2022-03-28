package com.tracker;

import com.tracker.models.Project;
import com.tracker.repo.PostRepository;
import com.tracker.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.tracker.models.Post;

import java.util.*;

@Controller
public class BlogController {

    private Long s;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }
    
    // Getting data and adding it to the site
    
    @GetMapping("/output")
    public String output(Model model) {
        Iterable<Post> list = postRepository.findAll();
        model.addAttribute("list", list);
        Iterable<Project> list1 = projectRepository.findAll();
        model.addAttribute("list1", list1);
        return "output";
    }
    
    // Adding data to the database (tasks)
    
    @PostMapping("/index")
    public String postIndex(@RequestParam String task_name, @RequestParam String status, @RequestParam String description, @RequestParam int priority, Model model) {
        Post post = new Post(task_name, status, description, priority);
        postRepository.save(post);

        return "/index";
    }
    
    
    
    @GetMapping("/project")
    public String project(Model model) {
        Iterable<Project> proje = projectRepository.findAll();
        model.addAttribute("proje", proje);
        return "project";
    }
    
        // Adding data to the database (projects)

    @PostMapping("/project")
    public String postProject(@RequestParam String project_name, @RequestParam String status_project, @RequestParam String start_date, @RequestParam String completion_date,@RequestParam int priority_project) {

        Project project = new Project(project_name, status_project, priority_project, start_date, completion_date);
        projectRepository.save(project);

        return "/project";
    }

    @GetMapping("/index/{id}")
    public String indexId(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> list = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        list.ifPresent(res :: add);
        model.addAttribute("list", res);
        return "idDetails";
    }

        
    // Changing data
    
    @GetMapping("/index/{id}/edit")
    public String edit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/index";
        }
        Optional<Post> list = postRepository.findById(id);
        ArrayList<Post> rest = new ArrayList<>();
        list.ifPresent(rest :: add);
        model.addAttribute("list", rest);
        return "edit";
    }
    
    // Data modification and saving to the database (tasks)
    
    @PostMapping("/index/{id}/edit")
    public String postUpdate(@PathVariable(value = "id") long id, @RequestParam String task_name, @RequestParam String status, @RequestParam String description, @RequestParam int priority, Model model) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(Exception::new);
        post.setTask_name(task_name);
        post.setStatus(status);
        post.setDescription(description);
        post.setPriority(priority);
        postRepository.save(post);
        return "redirect:/output";
    }
    
    
        
    @GetMapping("/project/{id}")
    public String projectId(@PathVariable(value = "id") long id, Model model) {
        Optional<Project> list = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        list.ifPresent(res :: add);
        model.addAttribute("list", res);
        return "idDetailsProject";
    }

    // Data modification (projects)
    
    @GetMapping("/project/{id}/editProject")
    public String editProject(@PathVariable(value = "id") long id, Model model) {
        if (!projectRepository.existsById(id)) {
            return "redirect:/output";
        }
        Optional<Project> list = projectRepository.findById(id);
        ArrayList<Project> rest = new ArrayList<>();
        list.ifPresent(rest :: add);
        model.addAttribute("list", rest);
        return "editProject";
    }
    
     // Data modification and saving to the database (projects)
    
    @PostMapping("/project/{id}/editProject")
    public String projectUpdate(@PathVariable(value = "id") long id, @RequestParam String project_name, @RequestParam String status_project, @RequestParam String start_date, @RequestParam String completion_date, @RequestParam int priority_project, Model model) throws Exception {

        Project project = projectRepository.findById(id).orElseThrow(Exception::new);
        project.setPriority(priority_project);
        project.setStatus(status_project);
        project.setProject_name(project_name);
        project.setCompletion_date(completion_date);
        project.setStart_date(start_date);
        projectRepository.save(project);
        return "redirect:/output";
    }
    
    // Deleting data
    
    @PostMapping("/index/{id}/delete")
    public String deleteIndex(@PathVariable(value = "id") long id, Model model) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(Exception::new);
        postRepository.delete(post);
        return "redirect:/output";
    }
    @PostMapping("/project/{id}/deleteProject")
    public String deleteProject(@PathVariable(value = "id") long id, Model model) throws Exception {
        Project project = projectRepository.findById(id).orElseThrow(Exception::new);
        projectRepository.delete(project);
        return "redirect:/output";
    }
}
