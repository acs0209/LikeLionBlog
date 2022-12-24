블로그
------------

역할
-----------
'''
##제공 서비스	 
1. 질문 글 서비스 - 사용자가 질문 글을 crud 이용
2. 답변 글 서비스  - 사용자가 답글을 crud 이용
3. 댓글 서비스  - 사용자가 댓글을 crud 이용
4. 회원가입 서비스 - 사용자가 회원가입 기능 이용
  + DB에 저장하는 비밀번호는 스프링 시큐리티를 사용하여 암호화하여 저장
  + 모든 글은 작성할때 사용자 이름이 필요하다 입력한 사용자 이름이 DB에 없을 경우 글을 작성할 수 없다.
  + DB에 이미 사용자 이름이나 이메일이 있을 경우 화면에 이미 존재하는 사용자라고 오류 메시지 띄우기
'''

#기술 스택
---------
###Backend
  -Java 11
  -Spring Boot
  -Spring Web
  -Spring Data JPA
  -Spring Security
###Frontend
  -Spring Thymeleaf
  -Bootstrap(HTML, CSS)
###DB
  -MySQL
  
#아키 텍처
---------
<img width="524" alt="아키텍처" src="https://user-images.githubusercontent.com/69111959/209432870-7036ac44-bbc2-4ae5-b3f3-d7817d1d10f0.png">



