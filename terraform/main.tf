
resource "null_resource" "create_kind_cluster" {
  provisioner "local-exec" {
    command     = "#!/bin/bash\nset -e\nif kind get clusters | grep -q \"^my-cluster$\"; then\n  kind delete cluster --name my-cluster\nfi\nkind create cluster --name my-cluster --config=../create_cluster.yml"
    interpreter = ["/bin/bash", "-c"]
  }

  triggers = {
    config_sha = filesha256("../create_cluster.yml")
  }
}