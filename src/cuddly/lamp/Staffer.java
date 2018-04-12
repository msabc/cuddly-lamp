/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuddly.lamp;

/**
 *
 * @author programer10
 */
public class Staffer{
        private int id;
        private String firstName;
        private String lastName;
        private String nickName;
        private int salary;

        public Staffer(int id, String firstName, String lastName, String nickName, int salary) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.nickName = nickName;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Staffer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nickName=" + nickName + ", salary=" + salary + '}';
        }
        
        
    }
