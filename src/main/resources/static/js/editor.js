tinymce.init({
    selector: 'textarea',
    height: 500,
    // menubar: false,
    language: 'pt_BR',
    plugins: [
      'autolink lists link image charmap preview textcolor',
      'searchreplace fullscreen',
      'insertdatetime media table contextmenu paste wordcount',
      'fullpage  directionality',
      'visualchars template codesample',
      'hr advlist ',
      'imagetools colorpicker',
      'textpattern help',
    ],
    toolbar: [' insert | formatselect | bold italic forecolor backcolor | link | alignleft aligncenter alignright alignjustify  | bullist numlist outdent indent  | removeformat image | undo redo']
  });