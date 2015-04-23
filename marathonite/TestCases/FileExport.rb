#{{{ Marathon
require_fixture 'default'
#}}} Marathon

=begin
Tests sample use case
=end

def test

    $java_recorded_version="1.8.0_40"
    with_window("Excel Data Extractor") {
        click("Add Files")

        with_window("Open") {
            select("JFileChooser_0", "#M/Fixtures")
        }

        click("Deselect All")
        sleep(2)
        select("JList_0", "[{12, true}]")
        click("Export")

        with_window("Save") {
            select("JFileChooser_0", "#M/TestData/testresults")
        }

        with_window("Message") {
            assert_p("lbl:File successfully exported", "Text", "File successfully exported")
        }
    }

end
