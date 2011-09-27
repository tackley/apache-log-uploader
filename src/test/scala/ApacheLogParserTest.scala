
import io.Source
import org.specs2.execute.Success
import org.specs2.mutable.Specification


class ApacheLogParserTest extends Specification {
  val sampleLog = """
10.251.26.180 - - [26/Sep/2011:04:02:01 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=&key=http%3A%2F%2Fgu.com%2Fp%2F326e4&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fpolitics%2F2011%2Fsep%2F26%2Fed-balls-labour-stance-deficit&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 153287 "-" "Jakarta Commons-HttpClient/3.1" 803142
10.251.26.183 - - [26/Sep/2011:04:02:02 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=&key=http%3A%2F%2Fgu.com%2Fp%2F325vz&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fmedia%2Faudio%2F2011%2Fsep%2F23%2Fmedia-talk-podcast-press-freedom-harold-evans&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 9564 "-" "Jakarta Commons-HttpClient/3.1" 69244
10.251.26.180 - - [26/Sep/2011:04:02:02 +0100] "GET /discussion-microapp/guardian.co.uk/api/fetchJsonLatest3CommentsForKey?key=&key=%2Fp%2F324dp HTTP/1.1" 200 373 "-" "Jakarta Commons-HttpClient/3.1" 44702
10.251.26.183 - - [26/Sep/2011:04:02:01 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=&key=http%3A%2F%2Fgu.com%2Fp%2F2zgge&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fpolitics%2Fblog%2F2011%2Fapr%2F19%2Fpolitics-live-blog&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 139442 "-" "Jakarta Commons-HttpClient/3.1" 916203
10.251.26.183 - - [26/Sep/2011:04:02:02 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchPostCommentForm?key=http%3A%2F%2Fgu.com%2Fp%2F2hzm8&profile=%25notloggedin%25&profile-id=&msg=&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Flifeandstyle%2F2010%2Fjun%2F27%2Fmens-health-weight HTTP/1.1" 200 5217 "-" "Jakarta Commons-HttpClient/3.1" 39873
10.251.26.183 - - [26/Sep/2011:04:02:02 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=&key=http%3A%2F%2Fgu.com%2Fp%2F3257m&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fscience%2F2011%2Fsep%2F22%2Ffaster-than-light-particles-neutrinos&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 129433 "-" "Jakarta Commons-HttpClient/3.1" 167474
10.251.26.180 - - [26/Sep/2011:04:02:02 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchPostCommentForm?key=http%3A%2F%2Fgu.com%2Fp%2F2zgge&profile=%25notloggedin%25&profile-id=&msg=&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fpolitics%2Fblog%2F2011%2Fapr%2F19%2Fpolitics-live-blog HTTP/1.1" 200 5217 "-" "Jakarta Commons-HttpClient/3.1" 50810
10.251.26.182 - - [26/Sep/2011:04:02:01 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=19&key=http%3A%2F%2Fgu.com%2Fp%2F3259t&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Fcommentisfree%2F2011%2Fsep%2F23%2Fyou-tell-us&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 71245 "-" "Jakarta Commons-HttpClient/3.1" 1254492
10.251.26.181 - - [26/Sep/2011:04:02:01 +0100] "GET /discussion-microapp/guardian.co.uk/api/fetchJsonLatest3CommentsForKey?key=&key=%2Fp%2F3xhj6 HTTP/1.1" 200 3116 "-" "Jakarta Commons-HttpClient/3.1" 1395315
10.251.26.182 - - [26/Sep/2011:04:02:03 +0100] "GET /discussion-microapp/guardian.co.uk/components/fetchCommentsForKey?commentpage=&key=http%3A%2F%2Fgu.com%2Fp%2F2mtaq&page-url=http%3A%2F%2Fwww.guardian.co.uk%2Ffootball%2F2011%2Fjan%2F22%2Fwolves-liverpool-kenny-dalglish&signed-up=no&cachebust=&isliveblogging=false HTTP/1.1" 200 131529 "-" "Jakarta Commons-HttpClient/3.1" 175982
10.251.26.182 - - [26/Sep/2011:04:02:01 +0100] "GET /discussion-microapp/guardian.co.uk/api/getCommentCounts?short-urls=%2Fp%2F326d4%2C%2Fp%2F326f2%2C%2Fp%2F326fx%2C&cachebust=&callback=commentCountCallback HTTP/1.1" 200 279 "-" "Jakarta Commons-HttpClient/3.1" 1533583
"""


  "apache log parser" should {
    "present log as stream of log events" in {
      val eventStream = ApacheLogParser.fromSource(Source.fromString(sampleLog))
      val top2 = eventStream.take(2)
      top2 must have size(2)
    }
  }
}