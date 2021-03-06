package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import pages.ProjectCreationPage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ProjectTest extends BaseTest {

    static Faker faker = new Faker();

    @TmsLink("30")
    @Test(description = "Check if the project can be created")
    public void projectShouldBeCreated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        dashboardPage.open();
        assertTrue(dashboardPage.isProjectExist(projectName), "Project was not created");
    }

    @TmsLink("31")
    @Test(description = "Check if the project can be updated")
    public void projectShouldBeUpdated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String newProjectName = faker.book().title() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        String newProjectAnnouncement = faker.book().genre();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        administrationPage.isPageOpened();
        administrationPage.editProject(projectName);
        projectCreationPage.updateProject(newProjectName, newProjectAnnouncement, ProjectCreationPage.Type.SINGLE);
        dashboardPage.open();
        assertTrue(dashboardPage.isProjectExist(newProjectName), "Project was not updated");
    }

    @TmsLink("32")
    @Test(description = "Check if the project can be deleted")
    public void projectShouldBeDeleted() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();

        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement, ProjectCreationPage.Type.MULTIPLY);
        administrationPage.isPageOpened();
        administrationPage.deleteProject(projectName);
        administrationPage.confirmDeleteProject();
        dashboardPage.open();
        assertFalse(dashboardPage.isProjectExist(projectName), "Project has not been deleted");
    }
}