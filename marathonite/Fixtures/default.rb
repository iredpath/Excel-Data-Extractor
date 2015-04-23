=begin
Launcher uses the properties specified here to launch the application
=end

#{{{ Fixture Properties
fixture_properties = {
        'marathon.project.launcher.model' => "net.sourceforge.marathon.runtime.RuntimeLauncherModel",
        'marathon.application.mainclass' => "edu.neu.EDE.gui.GuiController",
        'marathon.application.arguments' => "",
        'marathon.application.vm.arguments' => "",
        'marathon.application.vm.command' => "",
        'marathon.application.working.dir' => "",
        'marathon.application.classpath' => "%marathon.project.dir%\\Excel-Data-Extractor.jar",
        'marathon.recorder.namingstrategy' => "net.sourceforge.marathon.objectmap.ObjectMapNamingStrategy",
        'marathon.fixture.reuse' => ""
    }
#}}} Fixture Properties

=begin
Default Fixture
=end

class Fixture

=begin
    Marathon executes this method at the end of test script.
=end

    def teardown
        
    end

=begin
    Marathon executes this method before the test script.
=end

    def setup
    end

=begin
    Marathon executes this method after the first window of the application is displayed.
    You can add any Marathon script elements here.
=end

    def test_setup
        
    end

end

$fixture = Fixture.new
