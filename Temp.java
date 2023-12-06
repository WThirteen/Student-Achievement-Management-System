

import java.util.ArrayList;
import java.util.Scanner;
class Temp {
    public static void main(String[] args) {

        class StudentGradeRecord {
            int id;
            String name;
            String studentId;
            String courseName;
            double score;

            public StudentGradeRecord(int id, String name, String studentId, String courseName, double score) {
                this.id = id;
                this.name = name;
                this.studentId = studentId;
                this.courseName = courseName;
                this.score = score;
            }
        }

        class StudentGradeManager {
            ArrayList<StudentGradeRecord> records = new ArrayList<>();

            public void addRecord(StudentGradeRecord record) {
                records.add(record);
            }

            public void deleteRecord(String studentId) {
                records.removeIf(record -> record.studentId.equals(studentId));
            }

            public void updateRecord(String studentId, String courseName, double newScore) {
                for (StudentGradeRecord record : records) {
                    if (record.studentId.equals(studentId) && record.courseName.equals(courseName)) {
                        record.score = newScore;
                        break;
                    }
                }
            }

            public StudentGradeRecord findRecord(String studentId) {
                for (StudentGradeRecord record : records) {
                    if (record.studentId.equals(studentId)) {
                        return record;
                    }
                }
                return null;
            }

            public void displayAllRecords() {
                for (StudentGradeRecord record : records) {
                    System.out.println("序号：" + record.id + "，姓名：" + record.name + "，学号：" + record.studentId + "，课程名称：" + record.courseName + "，成绩：" + record.score);
                }
            }

            public void sortByScore() {
                records.sort((r1, r2) -> Double.compare(r2.score, r1.score));
            }
            public void statistics(String courseName){
                double temp_all=0;
                int temp_n=0;
                double Max=-1;
                double Min=999;
                for (StudentGradeRecord record : records) {
                    if (record.courseName.equals(courseName)){
                        temp_all=temp_all + record.score;
                        temp_n++;
                        if (Min> record.score){
                            Min= record.score;
                        }
                        if (Max< record.score){
                            Max= record.score;
                        }
                    }
                }
                double average_value;
                average_value=temp_all/temp_n;
                System.out.println(courseName+"的平均分为："+average_value+"最高分为："+Max+"最低分为："+Min);
            }
        }
        StudentGradeManager manager = new StudentGradeManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作：1.添加学生成绩记录 2.删除学生成绩记录 3.修改学生成绩记录 4.查找学生成绩 5.查看所有学生成绩记录 6.按成绩从高往低排序 7.统计某门课平均分、最高分和最低分 8.退出");
            int operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("请输入学生信息（序号 姓名 学号 课程名称 成绩）：");
                    int id = scanner.nextInt();
                    String name = scanner.next();
                    String studentId = scanner.next();
                    String courseName = scanner.next();
                    double score = scanner.nextDouble();
                    manager.addRecord(new StudentGradeRecord(id, name, studentId, courseName, score));
                    break;
                case 2:
                    System.out.println("请输入要删除的学生学号：");
                    String deleteStudentId = scanner.next();
                    manager.deleteRecord(deleteStudentId);
                    break;
                case 3:
                    System.out.println("请输入要修改的学生学号和课程名称：");
                    String updateStudentId = scanner.next();
                    String updateCourseName = scanner.next();
                    System.out.println("请输入新的成绩：");
                    double newScore = scanner.nextDouble();
                    manager.updateRecord(updateStudentId, updateCourseName, newScore);
                    break;
                case 4:
                    System.out.println("请输入要查找的学生学号：");
                    String findStudentId = scanner.next();
                    StudentGradeRecord record = manager.findRecord(findStudentId);
                    if (record != null) {
                        System.out.println("序号：" + record.id + "，姓名：" + record.name + "，学号：" + record.studentId + "，课程名称：" + record.courseName + "，成绩：" + record.score);
                    } else {
                        System.out.println("未找到该学生的成绩记录");
                    }
                    break;
                case 5:
                    manager.displayAllRecords();
                    break;
                case 6:
                    manager.sortByScore();
                    System.out.println("成绩已按从高到低排序");
                    manager.displayAllRecords();
                    break;
                case 7:
                    System.out.println("请输入需要统计的科目名称：");
                    String class_name=scanner.next();
                    manager.statistics(class_name);
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("无效的操作，请重新选择");
            }
        }
    }
}
