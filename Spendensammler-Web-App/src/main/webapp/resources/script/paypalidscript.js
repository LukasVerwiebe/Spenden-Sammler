var insert = document.getElementById("formpay:paypalidinput").value;
//var insert = "Ad0OYsrvfJhJJFPCRwip14IvbsoCk03VN9IKdjyU2xEtXvU_DQhx1-3pOjc7bbwSNYQRlhcYgDc9YJHB";
var script = '<script src="https://www.paypal.com/sdk/js?client-id=' + insert +'&amp;disable-funding=sepa,card,sofort&amp;enable-funding=giropay&amp;currency=EUR"></script>';
document.write(script);
