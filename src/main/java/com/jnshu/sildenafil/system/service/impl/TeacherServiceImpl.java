package com.jnshu.sildenafil.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jnshu.sildenafil.system.domain.Teacher;
import com.jnshu.sildenafil.system.mapper.TeacherDao;
import com.jnshu.sildenafil.system.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jnshu.sildenafil.common.exception.ParamIsNullException;

import com.jnshu.sildenafil.common.exception.ServiceException;
import com.jnshu.sildenafil.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Taimur
 * @since 2018-10-31
 */
@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {
    private static final long NOW = System.currentTimeMillis();

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    /**
     * 后台查询老师昵称List
     * @return 老师昵称List
     */
    @Override
    public List getTeacherNameList() {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        List<Teacher> teacherList = teacherDao.selectList(teacherQueryWrapper);
        List teacherNameList = teacherList.stream().map(Teacher::getNickname).collect(Collectors.toList());
        log.info("result of getTeacherNameList is: {}", teacherNameList);
        return teacherNameList;
    }

    /**
     * 后台通过id查询老师详情
     * @param teacherId 老师id
     * @return 查询到的老师详情
     */
    @Override
    public Teacher getTeacherById(Long teacherId) {
        log.info("args for getTeacherById is: {}", teacherId);
        if (teacherId != null) {
            Teacher teacher = teacherDao.selectById(teacherId);
            log.info("result of getTeacherById is: {}", teacher);
            return teacher;
        } else {
            log.error("args is null!");
            return null;
        }
    }

    /**
     * 后台新增老师详情
     * @param teacher 老师
     * @return 新增后返回的老师详情
     */
    @Override
    public Teacher saveTeacher(Teacher teacher) throws ServiceException, ParamIsNullException {
        log.info("args for saveTeacher is: {}", teacher);
        if (teacher == null) {
            throw new ParamIsNullException("teacher is null");
        }
        ValidationUtils.validate(teacher);
        teacher.setCreateAt(NOW);
        teacher.setCreateBy("admin-lihoo");
        Long id = teacherDao.insert(teacher) > 0 ? teacher.getId() : -10000;
        log.info("result for saveTeacher success; result detail: teacherId={}; {}", id, teacher);
        return teacher;
    }

    /**
     * 后台删除老师
     * @param teacherId 老师id
     * @return 是否成功删除老师
     */
    @Override
    public Boolean removeTeacherById(Long teacherId) {
        log.info("args for removeTeacherById is: {}", teacherId);
        if (teacherId != null) {
            int flag = teacherDao.deleteById(teacherId);
            log.info("result of removeTeacherById is: {}", flag);
            return true;
        } else {
            log.error("args is null!");
            return null;
        }
    }
}
