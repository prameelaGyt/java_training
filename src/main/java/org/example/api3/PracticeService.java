package org.example.api3;

import org.example.api.Info;
import org.example.api.NotFoundException;
import org.example.api2.Group;
import org.example.api2.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PracticeService
{
    @Autowired
    private PracticeRepository practiceRepository;
    @Autowired
    private GroupRepository groupRepository;
    public List<Info> getAllPractices()
    {
        List<Practice> practices=practiceRepository.findAll();
        List<Info> practiceInfoSet=new ArrayList<>();
        for(Practice p: practices)
        {
            practiceInfoSet.add(new Info(p.getId(), p.getName()));
        }
        return practiceInfoSet;
       // return practiceRepository.findAll();
    }
    public Practice createPractice(Practice practice,int gid) throws NotFoundException
    {
        Group group=groupRepository.findById(gid).orElseThrow(()->new NotFoundException("Group not found"));
        practice.setGroup(group);
        group.getPractices().add(new Info(practice.getId(), practice.getName()));
        return practiceRepository.save(practice);
    }

    public Practice updatePractice(int pid, Practice practice) throws NotFoundException
    {
        if (!practiceRepository.existsById(pid))
        {
            throw new NotFoundException("Practice not found");
        }
        practice.setId(pid);
        return practiceRepository.save(practice);
    }

    public void deletePractice(int pid) throws NotFoundException
    {
        if (!practiceRepository.existsById(pid))
        {
            throw new NotFoundException("Practice not found");
        }
        practiceRepository.deleteById(pid);
    }
}
