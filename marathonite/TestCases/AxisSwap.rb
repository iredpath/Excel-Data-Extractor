#{{{ Marathon
require_fixture 'default'
#}}} Marathon

=begin
Tests axis swap functionality
=end

def test

    $java_recorded_version="1.8.0_40"
    with_window("Excel Data Extractor") {
        assert_p("lbl:Stimulus", "Text", "Stimulus")
        click("Swap")
        assert_p("lbl:Stimulus", "Text", "Subject")
    }

end
