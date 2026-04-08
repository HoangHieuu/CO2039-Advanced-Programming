import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Khởi tạo Transcript
            Transcript myTranscript = new Transcript();

            // 2. Thêm các môn học (Dữ liệu mẫu)
            myTranscript.addCourseResult(new CourseResult("CS101", 3, 9.0)); // Hệ 4: 4.0
            myTranscript.addCourseResult(new CourseResult("MA202", 4, 7.5)); // Hệ 4: 3.0
            myTranscript.addCourseResult(new CourseResult("PH303", 2, 4.5)); // Hệ 4: 1.0 (Trượt nếu < 5.0)

            // 3. Kiểm tra tính toán GPA
            // Công thức: ((4.0*3) + (3.0*4) + (1.0*2)) / (3 + 4 + 2) = 26 / 9 = 2.888... -> 2.9
            System.out.println("GPA (Scale 4.0): " + myTranscript.gpa4());

            // 4. Kiểm tra số môn trượt
            System.out.println("Failed Courses: " + myTranscript.failedCount());

            // 5. Kiểm tra tính đóng gói (Encapsulation)
            List<CourseResult> results = myTranscript.getResults();
            System.out.println("Total items in transcript: " + results.size());
            
            // Thử thay đổi danh sách trả về (Sẽ báo lỗi nếu dùng UnmodifiableList)
            // results.clear(); 

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}